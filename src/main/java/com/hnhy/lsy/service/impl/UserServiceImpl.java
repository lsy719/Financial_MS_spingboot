package com.hnhy.lsy.service.impl;

import com.hnhy.lsy.entity.User;
import com.hnhy.lsy.mapper.UserMapper;
import com.hnhy.lsy.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User userLogin(User user) {
        return userMapper.find(user);
    }

    @Override
    public User userCfm(User usr) {
        return userMapper.secondCfm(usr);
    }

    @Override
    public User userIdLoad(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public Integer changeHead(Integer id, String img_url) {
        return userMapper.changeImg(id,img_url);
    }

    @Override
    public Integer findAccEcho(Integer id,String account) {
        return userMapper.userAccEcho(id, account);
    }

    @Override
    public Integer changeAc(Integer id, String account) {
        return userMapper.changeAcc(id, account);
    }

    @Override
    public Integer changeNn(Integer id, String newName) {
        return userMapper.changeUn(id, newName);
    }

    @Override
    public Integer changePn(Integer id, String phone) {
        return userMapper.changePn(id, phone);
    }

    @Override
    public Integer changePwCheck(Integer id, String passwordOld) {
        return userMapper.cfmOldPassword(id, passwordOld);
    }

    @Override
    public Integer changePw(Integer id, String passwordNew) {
        return userMapper.changePassword(id, passwordNew);
    }

    @Override
    public Integer countCards(Integer id) {
        return userMapper.bankCardsCount(id);
    }

    @Override
    public Integer modPocketMoney(Double newMoney, Integer userId) {
        return userMapper.changePocketMoney(newMoney, userId);
    }

    @Override
    public Integer checkPhoneCheck(Integer userId, String newPhone) {
        return userMapper.checkPhoneOccur(userId, newPhone);
    }
}
