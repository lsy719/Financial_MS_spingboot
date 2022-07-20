package com.hnhy.lsy.controller;

import com.alibaba.fastjson.JSON;
import com.hnhy.lsy.common.Result;
import com.hnhy.lsy.entity.User;
import com.hnhy.lsy.service.ICardsService;
import com.hnhy.lsy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequestMapping("/user")
@RestController
public class LoginController {
    @Value("${server.port}")
    private String port;

    //    IP写死
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

    private RedisTemplate redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Resource
    private ICardsService cardsService;
    @Resource
    private IUserService userService;


    public static String get8UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){
        User res = userService.userLogin(user);
        if(res == null){
            return Result.error("-1","用户名或密码错误");
        }
        String thisKey = "userVerifyCode" + res.getId();
        String thisVerifyCode = get8UUID();
        redisTemplate.opsForValue().set(thisKey,thisVerifyCode,60*60*24*7, TimeUnit.SECONDS);
        String returnLocalStorage = res.getId() + "-" + thisVerifyCode;
//        把查到的用户信息放在 res 里传到前端去
//        return Result.success(res);
        return Result.success(returnLocalStorage);
    }

    @PostMapping("/codeonly")
    public Integer userCodeOnly(Integer id, String code){
        String userInfoKey = "userInfo-" + id;
        String verifyKey = "userVerifyCode" + id;
        String verifyCode = (String) redisTemplate.opsForValue().get(verifyKey);
        if(verifyCode.equals(code)){
            return 1;
        }else{
            return 0;
        }
    }

    @PostMapping("/code")
    public Result<?> userCode(Integer id, String code){
        String userInfoKey = "userInfo-" + id;
        String verifyKey = "userVerifyCode" + id;
        String verifyCode = (String) redisTemplate.opsForValue().get(verifyKey);
        if(verifyCode.equals(code)){
            if(redisTemplate.hasKey(userInfoKey)){
                User userInfo = JSON.parseObject(redisTemplate.opsForValue().get(userInfoKey).toString(),User.class);
                return Result.success(userInfo);
            }else{
                User res = userService.userIdLoad(id);
                if(res == null){
                    return Result.error("-1","errot");
                }else{
                    redisTemplate.opsForValue().set(userInfoKey,JSON.toJSON(res),60*60*24*3, TimeUnit.SECONDS);
                }
                return Result.success(res);
            }
        }else{
            return Result.error("-1","error");
        }
    }

    @PostMapping("/proof")
    public Result<?> proof(@RequestBody User user){
        User res = userService.userCfm(user);
        if(res == null){
            return Result.error("-1","账户不存在");
        }
        return Result.success(res);
    }

    @PostMapping("/loaduser")
    public Result<?> reload(Integer id){
        String userInfoKey = "userInfo-" + id;
        if(redisTemplate.hasKey(userInfoKey)){
            User userInfo = JSON.parseObject(redisTemplate.opsForValue().get(userInfoKey).toString(),User.class);
//            System.out.println("get from redis!!!");
            return Result.success(userInfo);
        }else{
            User res = userService.userIdLoad(id);
//        System.out.println(id);
            if(res == null){
                return Result.error("-1","账户不存在");
            }else{
                redisTemplate.opsForValue().set(userInfoKey,JSON.toJSON(res),60*60*24*3, TimeUnit.SECONDS);
            }
//            System.out.println("get from mysql!!!");
            return Result.success(res);
        }
    }

    @RequestMapping("/change_img")
    public Result<?> change_img(Integer id, MultipartFile file){
        String imgFile = null;
//        使用uuid生成一段随机数和原来的文件名拼接作为新的文件名。
        String newFile = UUID.randomUUID()+"_"+file.getOriginalFilename();
        File f = new File("/root/fms_img/account_img/",newFile);
//        判断上级目录是否存在，不存在则创建
        if(!f.getParentFile().exists()){
            f.mkdirs();
        }
//            将生成的新的文件对象转到目标文件对象上。(直接file.transferTo(f)会报错，要try catch)
        try {
//                将上传的文件存储到指定的磁盘位置
            file.transferTo(f);
//                前台想显示图片，要带上web访问图片的一种方式（页面不可能直接去访问物理路径），用之前配置util配置好的
//                记录上传文件的web访问路径
            imgFile = "http://" + ip + ":" + port + "/accountimg/" + newFile;//数据库里面也存储这个
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer state = userService.changeHead(id,imgFile);
        String userInfoKey = "userInfo-" + id;
        if(state == 0){
            return Result.error("-1","更新失败");
        }else{
            redisTemplate.delete(userInfoKey);
            return Result.success(imgFile);
        }
    }

    private String createUserInfoKey(Integer userId){
        return "userInfo-" + userId;
    }

//    修改用户账号
    @RequestMapping("/updacc")
    public Integer changeAc(Integer id, String account){
//        查询验证账号是否已经存在
//        进行update
        String userInfoKey = "userInfo-" + id;
        int flag = userService.findAccEcho(id,account);
        if(flag > 0){
            return 555;
        }else{
            int s = userService.changeAc(id,account);
            redisTemplate.delete(userInfoKey);
//            System.out.println(s);
            return 1;
        }
    }

//    修改用户名
    @RequestMapping("/updnn")
    public Integer changeNn(Integer id, String newName){
        redisTemplate.delete(createUserInfoKey(id));
        int s = userService.changeNn(id, newName);
        return s;
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
    //模拟收到验证码
    @RequestMapping("/simcodeget")
    public String getCodeSim(String phone){
        String codeKey = "VerifyCode" + phone +":code";
        String redisCode = (String) redisTemplate.opsForValue().get(codeKey);
//        System.out.println(phone);
//        System.out.println(redisCode);
        return redisCode;
    }
    @RequestMapping("/resend")
    public Integer reSendCode(String phone){
        verifyCode(phone);
        return 1;
    }
//    修改手机号
    @RequestMapping("/updp")
    public Integer changeP(Integer id, String phone, String oldPhone){
        // 查重
        if(userService.checkPhoneCheck(id, phone) == 0){
            // 别人注册时卡住的手机号不能注册
            String phoneKey = "userPhone" + oldPhone;
            if(redisTemplate.hasKey(phoneKey)){
                return -44;
            }
            // 修改后三天之内不能修改
//            int s = userService.changePn(id, phone);
            verifyCode(phone);
//            String phoneKeyNew = "userPhone" + phone;
//            redisTemplate.opsForValue().set(phoneKeyNew, "1", 60*60*24*3, TimeUnit.SECONDS);
            return 1;
        }else {
            return -43;
        }
    }

    //验证码验证，改变
    @RequestMapping("/checkcode")
    public Integer checkValidCode(Integer id, String phone, String code){
        if(getRedisCode(phone, code) == 1){
            return changePn(id, phone);
        }else {
            return 4;
        }
    }

    private Integer changePn(Integer id, String phone){
        int s = userService.changePn(id, phone);
        redisTemplate.delete(createUserInfoKey(id));
        String phoneKeyNew = "userPhone" + phone;
        redisTemplate.opsForValue().set(phoneKeyNew, "1", 60*60*24*3, TimeUnit.SECONDS);//3天
        return s;
    }

//    修改密码
    @RequestMapping("/updpw")
    public Integer changePw(Integer id, String passwordOld, String passwordNew){
        int s = userService.changePwCheck(id, passwordOld);
        if(s == 1){
            int res = userService.changePw(id, passwordNew);
            redisTemplate.delete(createUserInfoKey(id));
            return res;
        }else{
            return 4;
        }
    }
//    查询银行卡数量
    @RequestMapping("/cardscount")
    public Integer countCards(Integer id){
        int s = userService.countCards(id);
        return s;
    }

//    更改用户余额
    @RequestMapping("/changepm")
    public Integer changePM(Double newMoney, Integer userId){
        int res = userService.modPocketMoney(newMoney, userId);
        if(res == 1){
            redisTemplate.delete(createUserInfoKey(userId));
            return res;
        }else{
            return 444;
        }
    }
}
