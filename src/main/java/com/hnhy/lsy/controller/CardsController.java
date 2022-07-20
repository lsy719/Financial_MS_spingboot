package com.hnhy.lsy.controller;

import com.hnhy.lsy.common.Result;
import com.hnhy.lsy.entity.CardNote;
import com.hnhy.lsy.entity.UserCards;
import com.hnhy.lsy.service.ICardsService;
import com.hnhy.lsy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("/cards")
@RestController
public class CardsController {
    @Value("${server.port}")
    private String port;

//        IP写死
//    private static final String ip = "http://localhost";
//    private static final String ip = "http://192.168.163.100";

    private static String ip = null;

    static {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得8个长度的十六进制的UUID
     * @return UUID
     */
    public static String get8UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }

    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Resource
    private ICardsService cardsService;

    @PostMapping("/loadcards")
    public Result<?> myallcards(Integer id){
        List<UserCards> res = cardsService.queryCardsById(id);
//        System.out.println(res);
        if(res == null){
            return Result.error("-1","未知错误");
        }
        return Result.success(res);
    }
    @PostMapping("/getNote")
    public Result<?> cardNotes(){
        String cardNotes = "cardNotes";
        if(redisTemplate.hasKey(cardNotes)){
            List<CardNote> cardNoteList = redisTemplate.opsForList().range(cardNotes,0,-1);
            return Result.success(cardNoteList);
        }
        List<CardNote> res = cardsService.queryCardNotes();
        if(res == null){
            return Result.error("-1","未知错误");
        }else{
            for (CardNote note : res){
                redisTemplate.opsForList().rightPush(cardNotes,note);
            }
            redisTemplate.expire(cardNotes,60*10,TimeUnit.SECONDS);
            return Result.success(res);
        }
    }
    @PostMapping("/modNote")
    public Integer cardNoteMod(Integer cardId, Integer userId, String cardNote){
        int res = cardsService.changeCardNote(cardId,userId,cardNote);
        if(res == 1){
            return res;
        }else{
            return 555;
        }
    }
    @PostMapping("/onecard")
    public Result<?> onecardpage(Integer cardId, Integer userId){
        UserCards res = cardsService.getOneCard(cardId, userId);
        if(res == null){
            return Result.error("-1","未知错误");
        }
        return Result.success(res);
    }
    @PostMapping("/modmoney")
    public Integer moneychange(Double cardMoney, Integer cardId, Integer userId){
        int res = cardsService.modifyMoney(cardMoney, cardId, userId);
        if(res == 1){
            return res;
        }else{
            return 555;
        }
    }

    /**
     * @param userId
     * @param cardKey
     * @param cardName
     * @param cardMoney
     * @param bankCode
     * @param bankName
     * @return
     * 提交过来的情况
     * 必带：userId、cardKey、cardName，cardMoney为0也每关系，往里存即可
     * 空值：bankCode和bankName，要判断，如果为空，则setBankCode为"UNKNOWN"，然后只往usercards表添加即可
     *
     * 如果不为空，先检查bankCode和bankName是否存在，不对，只检查bankCode是否存在，bankCode需要唯一性，
     * 如果bankCode不存在，将bankCode和bankName添加进bank表
     *
     * 前端做了控制，bankCode和bankName要么同时有，要么同时为空
     *
     * 如果存在，（bankCode和bankName出现了一对多的情况），怎么办？不可能！！！！
     * 存在就不要管了，只往usercards表添加
     *
     */
    @PostMapping("/addbankcard")
    public Integer addcard(Integer userId,
                           String cardKey,
                           String cardName,
                           Double cardMoney,
                           String bankCode,
                           String bankName)
    {
//        System.out.println("userId:"+userId);
//        System.out.println("cardKey:"+cardKey);
//        System.out.println("cardName:"+cardName);
//        System.out.println("cardMoney:"+cardMoney);
//        System.out.println("bankCode:"+bankCode);
//        System.out.println("bankName:"+bankName);
//        if(cardMoney == 0.0) System.out.println("余额为0");
//        if(bankCode.length() == 0 && bankName.length() == 0)System.out.println("没有该银行");
        if (bankCode.length() == 0 && bankName.length() == 0){
            bankCode = "UNKNOWN";
            int cardEcho = cardsService.checkHasCard(userId, cardKey);
            if(cardEcho == 1){
                return 4;//该用户已经绑定该卡，不可重复绑定
            }else {
                cardsService.addUserCard(userId, cardKey, cardName, cardMoney, bankCode);
                return 1;
            }
        }else{
            int cardEcho = cardsService.checkHasCard(userId, cardKey);
            if(cardEcho == 1){
                return 4;//该用户已经绑定该卡，不可重复绑定
            }else {
                int bankEcho = cardsService.checkHasBank(bankCode);
                if(bankEcho == 1){
                    cardsService.addUserCard(userId, cardKey, cardName, cardMoney, bankCode);
                }else {
                    String bankImg = "/img/UNKNOWN.png";
                    cardsService.addBankInfo(bankCode,bankName,bankImg);
                    cardsService.addUserCard(userId, cardKey, cardName, cardMoney, bankCode);
                }
                return 1;
            }
        }
    }
    //    -----------------------------------------------------------
    private Integer addUser(String account,
                                String nick_name,
                                String phone,
                                String password,
                                MultipartFile file){
        //           按需要生成随机用户名和默认图片
        if(nick_name.length() == 0){
            String randomName = "用户" + get8UUID();;
//            System.out.println(randomName);
            nick_name = randomName;
        }
        String imgFile = null;
        if(file != null){
//            System.out.println("avatar:"+ file.getOriginalFilename());
            String newFile = UUID.randomUUID()+"_"+file.getOriginalFilename();
            File f = new File("/root/fms_img/account_img/",newFile);
            if(!f.getParentFile().exists()){
                f.mkdirs();
            }
            try {
                file.transferTo(f);
                imgFile = "http://" + ip + ":" + port + "/accountimg/" + newFile;//数据库里面也存储这个
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println(imgFile);
        }else{
//            默认图片路径
            imgFile = "/img/mr.png";
        }
        int s = cardsService.registerUser(account, password, nick_name, imgFile, phone, 0.00);
        String phoneKey = "userPhone" + phone;
        // 注册成功手机号3天不能更改
        redisTemplate.opsForValue().set(phoneKey, 1, 60*60*24*3, TimeUnit.SECONDS);
        if(s == 1){
            return s;
        }else{
            return 999;
        }
    }

    //生成随机6位验证码
    private String makeCode(){
        Random random = new Random();
        String code ="";
        for(int i = 0; i < 6; i++) {
            code += random.nextInt(10);
        }
        return code;
    }
    //对应手机号生成验证码
    private void verifyCode(String phone){
        String codeKey = "VerifyCode" + phone +":code";
        String vCode = makeCode();
        redisTemplate.opsForValue().set(codeKey, vCode,60*5, TimeUnit.SECONDS);
    }
    // 前端验证验证码
    private Integer getRedisCode(String phone,String code){
        String codeKey = "VerifyCode" + phone +":code";
        String redisCode = (String) redisTemplate.opsForValue().get(codeKey);
        if(redisCode.equals(code)){
            return 1;
        }else{
            return 0;
        }
    }
    //当前正在注册账号与手机号（肯定是同时）
    private void nowRegisteInfoAdd(String account,String phone){
        String nowRegisteAccount = "account" + "-" + account;
        String nowRegistePhone = "phone" + "-" + phone;
        redisTemplate.opsForValue().set(nowRegisteAccount, "1",60*5, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(nowRegistePhone, "1",60*5, TimeUnit.SECONDS);
    }
    // 当前正在被注册的手机号（5分钟）
    private Integer nowRegisteInfoCheck(String account, String phone){
        String nowRegisteAccount = "account" + "-" + account;
        String nowRegistePhone = "phone" + "-" + phone;
        int res = 0;
        if(redisTemplate.hasKey(nowRegisteAccount)){
            res = 1;
        }
        if(redisTemplate.hasKey(nowRegistePhone)){
            res = 1;
        }
        return res;
    }
    @RequestMapping("/simcodeget")
    public String getCodeSim(String phone){
        String codeKey = "VerifyCode" + phone +":code";
        String redisCode = (String) redisTemplate.opsForValue().get(codeKey);
        return redisCode;
    }

    @RequestMapping("/registeDone")
    public Integer nowRegisteInfoDone(String account, String phone){
        String nowRegisteAccount = "account" + "-" + account;
        String nowRegistePhone = "phone" + "-" + phone;
        redisTemplate.delete(nowRegisteAccount);
        redisTemplate.delete(nowRegistePhone);
        return 0;
    }

    @RequestMapping("/registeradd")
    public Integer register(String account,
                            String phone)
    {
//        System.out.println("account:"+account);
//        System.out.println("nick_name:"+nick_name);
//        System.out.println("phone:"+phone);
//        System.out.println("password:"+password);
        if(cardsService.checkHasAccount(account) == 0){//账号不存在，可以注册
            if(cardsService.checkHasPhone(phone) == 0){
                if(nowRegisteInfoCheck(account, phone) == 1){
                    return 6;
                }else{
                    nowRegisteInfoAdd(account, phone);
                    String codeKey = "VerifyCode" + phone +":code";
                    String redisCode = (String) redisTemplate.opsForValue().get(codeKey);
//                System.out.println(redisCode);
                    if(redisCode == null){
                        verifyCode(phone);
                    }
                    return 1;
                }
                // return addUser(account, nick_name, phone, password, file);
            }else {
                return 5;
            }
        }else {
            return 4;
        }
    }

    @RequestMapping("/verifyfin")
    public Integer finalStep(String account,
                             String nick_name,
                             String phone,
                             String password,
                             MultipartFile file,
                             String verifyCode){
        if(getRedisCode(phone,verifyCode) == 1){
            //正确
            int res = addUser(account, nick_name, phone, password, file);
            return res;//1 or 999
        }else{
            return 555;
        }
    }
    @RequestMapping("/clearcode")
    public Integer clearCode(String phone){
        String codeKey = "VerifyCode" + phone +":code";
        redisTemplate.delete(codeKey);
        return 0;
    }
    // 重新发送
    @RequestMapping("/resend")
    public Integer reSendCode(String phone){
        verifyCode(phone);
        return 0;
    }
//    --------------------------------------------------------------------------------


    @RequestMapping("unbindcard")
    public Integer disbind(Integer userId,
                           String cardKey,
                           String cardName,
                           Double disbindMoney,
                           Date disbindDate,
                           Integer cardId){
//        首先也要检查usercardsinbind表是否已经存在这个用户的这张卡，存在就改一下money和时间.agoId就行
//        int hasUnbundCard = cardsService.checkHasUnbundCard(userId, cardKey);
//        if(hasUnbundCard == 1){
//            int updres = cardsService.updUnbindCard(disbindMoney, disbindDate, cardId, userId, cardKey);
//            if(updres == 1){
//                int delres = cardsService.delcard(cardId);
//                if (delres == 1){
//                    return 1;
//                }else {
//                    return 555;
//                }
//            }else{
//                return 555;
//            }
//        }else{
//        不需要改，之前解绑再添加再解绑的卡，每一个都有agoId的，每一个这个agoId都可能带着余额基金的记录
            int addres = cardsService.addunbindCard(userId, cardKey, cardName, disbindMoney, disbindDate, cardId);
            if(addres == 1){
                int delres = cardsService.delcard(cardId);
                if (delres == 1){
                    return 1;
                }else {
                    return 555;
                }
            }else{
                return 555;
            }
//        }
    }

    @RequestMapping("getidjustadd")
    public Integer getcardid(Integer userId, String cardKey){
//        System.out.println(cardsService.getIdCardJustAdd(userId, cardKey));
        return cardsService.getIdCardJustAdd(userId, cardKey);
    }
}
