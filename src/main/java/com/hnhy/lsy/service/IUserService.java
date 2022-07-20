package com.hnhy.lsy.service;

import com.hnhy.lsy.entity.User;

public interface IUserService {
//    登陆
    User userLogin(User user);
//    验证
    User userCfm(User usr);
//    根据id获取用户
    User userIdLoad(Integer id);
//    更改头像
    Integer changeHead(Integer id, String img_url);
//    查询账号是否重复
    Integer findAccEcho(Integer id,String account);
//    更改账号
    Integer changeAc(Integer id,String account);
//    更改用户名
    Integer changeNn(Integer id, String newName);
//    更改手机号
    Integer changePn(Integer id, String phone);
//    更改密码前验证
    Integer changePwCheck(Integer id, String passwordOld);
//    更改密码
    Integer changePw(Integer id, String passwordNew);
//    查询银行卡数量
    Integer countCards(Integer id);

//    修改用户余额
    Integer modPocketMoney(Double newMoney, Integer userId);

    Integer checkPhoneCheck(Integer userId, String newPhone);
}
