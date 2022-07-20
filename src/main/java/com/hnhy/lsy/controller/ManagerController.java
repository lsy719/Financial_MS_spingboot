package com.hnhy.lsy.controller;

import com.hnhy.lsy.common.Result;
import com.hnhy.lsy.entity.*;
import com.hnhy.lsy.service.IManagerService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequestMapping("/manage")
@RestController
public class ManagerController {
    @Value("${server.port}")
    private String port;

    //    IP写死
//    private static final String ip = "http://localhost";

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
    private IManagerService managerService;

    private String createUserInfoKey(Integer userId){
        return "userInfo-" + userId;
    }

    @RequestMapping("/findlogin")
    public Result<?> findlogin(String account,String password){
        Manager res = managerService.managerLoginFirst(account, password);
        if(res == null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }

    @RequestMapping("/getmanager")
    public Result<?> getmanager(String account,String password,Integer id){
        Manager res = managerService.managerAfterLogin(account, password, id);
        if(res == null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(res);
    }

    @RequestMapping("/changepw")
    public Integer changepw(String pwOld, String pwNew, Integer id){
        int test = managerService.adminPwChangeOld(pwOld, id);
        if(test == 1){
            int res = managerService.adminPwChange(pwNew, id);
            if(res == 1){
                return res;
            }else{
                return -1;
            }
        }else {
            return -1;
        }
    }

    @RequestMapping("/managers")
    public Result<?> getNormalAdmin(String account, String password){
        Manager res = managerService.managerLoginFirst(account, password);
        if(res == null){
            return Result.error("-1","验证失败");
        }else {
            List<Manager> managerList = managerService.adminManagerList();
            return Result.success(managerList);
        }
    }

    @RequestMapping("/statechange")
    public Integer changeAdminState(String account, String password, Integer newState, Integer id){
        Manager proof = managerService.managerLoginFirst(account, password);
        if(proof == null){
            return -1;
        }else{
            int res = managerService.adminStateChange(newState, id);
            if(res == 1){
                return res;
            }else{
                return 555;
            }
        }
    }

    @RequestMapping("/adminadd")
    public Integer addManager(String account, String password, String createTime){
        int exist = managerService.accountExistCheck(account);
        if(exist != 0){
            return 2;
        }else{
            int res = managerService.addManager(account, password, createTime);
            return res;
        }
    }

    @RequestMapping("/usernum")
    public Integer getallusernum(){
        int res = managerService.adminGetUserCount();
        return res;
    }
    @RequestMapping("/alluser")
    public Result<?> getalluser(String account, String password,Integer anchor,Integer pageSize){
        Manager proof = managerService.managerLoginFirst(account, password);
        if(proof == null){
            return Result.error("-1","爬");
        }else{
            List<UserForAdmin> userList = managerService.adminGetUser(anchor, pageSize);
            userList.forEach((item) -> {
                item.setFundNum(managerService.countUserFund(item.getId()));
                item.setCardNum(managerService.countUserCard(item.getId()));
            });
            return Result.success(userList);
        }
    }

    @RequestMapping("/searchusernum")
    Integer searchUsernum(Integer searchType,String searchContent){
        int res = 0;
        if(searchType == 1){
            // 按id查询
            res = managerService.searchByIdCount(searchContent);
        }else if(searchType == 2){
            // 按账号查询
            res = managerService.searchByAccCount(searchContent);
        }else if(searchType == 3){
            // 按用户名查询
            res = managerService.searchByNameCount(searchContent);
        }
        return res;
    }
    @RequestMapping("/searchuser")
    public Result<?> searchUser(Integer searchType,String searchContent, Integer anchor,Integer pageSize){
        List<UserForAdmin> userList = new ArrayList<>();
        if(searchType == 1){
            // 按id查询
            userList = managerService.searchById(searchContent, anchor, pageSize);
        }else if(searchType == 2){
            // 按账号查询
            userList = managerService.searchByAcc(searchContent, anchor, pageSize);
        }else if(searchType == 3){
            // 按用户名查询
            userList = managerService.searchByName(searchContent, anchor, pageSize);
        }
        userList.forEach((item) -> {
            item.setFundNum(managerService.countUserFund(item.getId()));
            item.setCardNum(managerService.countUserCard(item.getId()));
        });
        return Result.success(userList);
    }

    @RequestMapping("/edituser")
    public Integer editUser(Integer isEdit, Integer userId, String updContent){
        int res = 0;
        if(isEdit == 1){
            // 冻结用户
            res = managerService.freezeUser(userId);
        }
        if(isEdit == 2){
            // 恢复用户
            res = managerService.refreezeUser(userId);
        }
        if(isEdit == 3){
            // 重置头像
            res = managerService.resetAvatar(userId, updContent);
        }
        if(isEdit == 4){
            // 重置用户名
            res = managerService.resetName(userId, updContent);
        }
        if (res != 0){
            redisTemplate.delete(createUserInfoKey(userId));
        }
        return res;
    }

    @RequestMapping("/allbanknum")
    public Integer getbanknum(){
        return managerService.getAllBankCount();
    }
    @RequestMapping("/allbanknumni")
    public Integer getbanknumni(){
        return managerService.getAllBankCountni();
    }
    @RequestMapping("/allbank")
    public Result<?> getbank(Integer anchor, Integer pageSize){
        List<Bank> bankList = managerService.getAllBank(anchor, pageSize);
        return Result.success(bankList);
    }
    @RequestMapping("/allbankni")
    public Result<?> getbankni(Integer anchor, Integer pageSize){
        List<Bank> bankList = managerService.getAllBankni(anchor, pageSize);
        return Result.success(bankList);
    }
    @RequestMapping("/searchbanknum")
    public Integer bankNumSearch(Integer searchType, String searchContent){
        int res = 0;
        if(searchType == 1){
            res = managerService.countBankCode(searchContent);
        }else if(searchType == 2){
            res = managerService.countBankName(searchContent);
        }else if(searchType == 4){
            res = managerService.countBankCodeNoImg(searchContent);
        }else if(searchType == 5){
            res = managerService.countBankNameNoImg(searchContent);
        }
        return res;
    }

    @RequestMapping("/searchbank")
    public Result<?> bankSearch(Integer searchType, String searchContent, Integer anchor, Integer pageSize){
        List<Bank> bankList = new ArrayList<>();
        if(searchType == 1){
            bankList = managerService.getBankByCode(searchContent, anchor, pageSize);
        }else if(searchType == 2){
            bankList = managerService.getBankByName(searchContent, anchor, pageSize);
        }else if(searchType == 4){
            bankList = managerService.getBankByCodeNoImg(searchContent, anchor, pageSize);
        }else if(searchType == 5){
            bankList = managerService.getBankByNameNoImg(searchContent, anchor, pageSize);
        }
        return Result.success(bankList);
    }

    @RequestMapping("/bankimgset")
    public Integer bankImgSet(Integer bankId, String bankCode, MultipartFile file){
        String imgFile = null;
        if(file != null){
//            System.out.println("avatar:"+ file.getOriginalFilename());
            String fileNameOrgin = file.getOriginalFilename();
            String imgName = fileNameOrgin.substring(fileNameOrgin.lastIndexOf("."));
            String newFile = get8UUID() + bankCode + imgName;
            File f = new File("/root/fms_img/bank_img/",newFile);
            if(!f.getParentFile().exists()){
                f.mkdirs();
            }
            try {
                file.transferTo(f);
                imgFile = "http://" + ip + ":" + port + "/bankimg/" + newFile;//数据库里面也存储这个
            } catch (IOException e) {
                e.printStackTrace();
            }
//            System.out.println(imgFile);
        }else{
//            默认图片路径
            imgFile = "/img/UNKNOWN.png";
        }
        int res = managerService.changeBankLogo(bankId,imgFile);
        if(res == 1){
            return res;
        }else{
            return 555;
        }
    }

//    ==================================================================================================================
//    ==================================================================================================================
//    ==================================================================================================================
//    ==================================================================================================================
//    ==================================================================================================================
    @PostMapping("/fundallnum")
    public Integer getAllFundNum(Integer searchType, String searchContent, Integer anchor, Integer pageSize){
        int num = 0;
        if(searchType == 0){
            // 获取全部
            num = managerService.getAllFundCount();
        }else{
            if(searchType == 1){
                // 按代码找
                num = managerService.getAllFundCodeCount(searchContent);
            }
            if(searchType == 2){
                // 按名称找
                num = managerService.getAllFundNameCount(searchContent);
            }
            if(searchType == 3){
                // 开头数字
                num = managerService.getAllFund1NumCount(searchContent);
            }
        }
        return num;
    }
    @PostMapping("/fundall")
    public Result<?> getAllFund(Integer searchType, String searchContent, Integer anchor, Integer pageSize){
        List<Finance> fundList = new ArrayList<>();
        // 往finance表里面找
        if(searchType == 0){
            // 获取全部
            fundList = managerService.getAllFund(anchor, pageSize);
        }else{
            if(searchType == 1){
                // 按代码找
                fundList = managerService.getAllFundCode(searchContent, anchor, pageSize);
            }
            if(searchType == 2){
                // 按名称找
                fundList = managerService.getAllFundName(searchContent, anchor, pageSize);
            }
            if(searchType == 3){
                // 开头数字
                fundList = managerService.getAllFund1Num(searchContent, anchor, pageSize);
            }
        }
        return Result.success(fundList);
    }
    @PostMapping("/fundapplynum")
    public Integer getUserApplynum(Integer searchType, String searchContent, Integer anchor, Integer pageSize){
        int num = 0;
        if(searchType == 0){
            // 获取全部
            num = managerService.getUserApplyCount();
        }else{
            if(searchType == 1){
                // 按代码找
                num = managerService.getUserApplyCodeCount(searchContent);
            }
            if(searchType == 2){
                // 按名称找
                num = managerService.getUserApplyNameCount(searchContent);
            }
            if(searchType == 3){
                // 开头数字
                num = managerService.getUserApply1NumCount(searchContent);
            }
        }
        return num;
    }
    @PostMapping("/fundapply")
    public Result<?> getUserApply(Integer searchType, String searchContent, Integer anchor, Integer pageSize){
        List<FundAddApply> fundList = new ArrayList<>();
        // 往fundaddapply里面找
        if(searchType == 0){
            // 获取全部
            fundList = managerService.getUserApply(anchor, pageSize);
        }else{
            if(searchType == 1){
                // 按代码找
                fundList = managerService.getUserApplyCode(searchContent, anchor, pageSize);
            }
            if(searchType == 2){
                // 按名称找
                fundList = managerService.getUserApplyName(searchContent, anchor, pageSize);
            }
            if(searchType == 3){
                // 开头数字
                fundList = managerService.getUserApply1Num(searchContent, anchor, pageSize);
            }
        }
        return Result.success(fundList);
    }

    @RequestMapping("/changename")
    public Integer changeFundName(Integer fundId, String newName){
        int res = managerService.changeNameAllFund(newName, fundId);
        String holdfundChooseKey = "holdFundToChoose-";
        Set<String> keys = redisTemplate.keys(holdfundChooseKey + "*");
        redisTemplate.delete(keys);
        redisTemplate.delete("fundAll");
        if(res == 1){
            return  res;
        }else{
            return 555;
        }
    }

    @RequestMapping("/adduserapply")
    public Integer addUserApply(String fundCode, String fundName){
        int res = managerService.fundExistCheck(fundCode);
        if(res > 0){
            return  -1;
        }
        // 1.往finance表中添加此数据
        int res1 = managerService.addFundUserApply(fundName, fundCode);
        redisTemplate.delete("fundAll");

        // 2.将fundaddapply中所有此代码的申请置为1
        int res2;
        if(res1 == 1){
            res2 = managerService.userApplyFinish(fundCode);
            return res2;// 应该是update的数量
        }
        return res1;
    }
    @RequestMapping("/applyreject")
    public Integer refuseUserApply(Integer applyId){
        int res = managerService.refuseUserApply(applyId);
        return res;
    }
    @RequestMapping("/codeexist")
    public Integer codeExistCheck(String fundCode){
        int res = managerService.fundExistCheck(fundCode);
        if(res > 0){
            return  -1;
        }else{
            return 0;
        }
    }
    @RequestMapping("/manageradd")
    public Integer managerAddFund(String fundCode,String fundName){
        int exist = managerService.fundExistCheck(fundCode);
        if(exist > 0){
            return  -1;
        }else{
            redisTemplate.delete("fundAll");
            return this.managerService.addFundUserApply(fundName, fundCode);
        }
    }
}
