package com.hnhy.lsy.service.impl;

import com.hnhy.lsy.entity.*;
import com.hnhy.lsy.mapper.ManagerMapper;
import com.hnhy.lsy.service.IManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManagerServiceImpl implements IManagerService {
    @Resource
    private ManagerMapper managerMapper;

    @Override
    public Manager managerLoginFirst(String account, String password) {
        return managerMapper.firstLogin(account, password);
    }

    @Override
    public Manager managerAfterLogin(String account, String password, Integer id) {
        return managerMapper.afterLogin(account, password, id);
    }

    @Override
    public Integer adminPwChangeOld(String pwOld, Integer id) {
        return managerMapper.changeAdminPasswprdOldPw(pwOld, id);
    }

    @Override
    public Integer adminPwChange(String pwNew, Integer id) {
        return managerMapper.changeAdminPassword(pwNew, id);
    }

    @Override
    public List<Manager> adminManagerList() {
        return managerMapper.getManagerList();
    }

    @Override
    public Integer adminStateChange(Integer newState, Integer id) {
        return managerMapper.changeAdminState(newState, id);
    }

    @Override
    public Integer accountExistCheck(String account) {
        return managerMapper.accountExist(account);
    }

    @Override
    public Integer addManager(String account, String password, String createTime) {
        return managerMapper.addAdmin(account, password, createTime);
    }

    @Override
    public Integer adminGetUserCount() {
        return managerMapper.getAlluserCount();
    }

    @Override
    public List<UserForAdmin> adminGetUser(Integer anchor, Integer pageSize) {
        return managerMapper.getAllUser(anchor, pageSize);
    }

    @Override
    public Integer searchByIdCount(String searchContent) {
        return managerMapper.searchUserByidCount(searchContent);
    }

    @Override
    public List<UserForAdmin> searchById(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.searchUserByid(searchContent, anchor, pageSize);
    }

    @Override
    public Integer searchByAccCount(String searchContent) {
        return managerMapper.searchUserByAccCount(searchContent);
    }

    @Override
    public List<UserForAdmin> searchByAcc(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.searchUserByAcc(searchContent, anchor, pageSize);
    }

    @Override
    public Integer searchByNameCount(String searchContent) {
        return managerMapper.searchUserByNameCount(searchContent);
    }

    @Override
    public List<UserForAdmin> searchByName(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.searchUserByName(searchContent, anchor, pageSize);
    }

    @Override
    public Integer countUserFund(Integer userId) {
        return managerMapper.countUserFund(userId);
    }

    @Override
    public Integer countUserCard(Integer userId) {
        return managerMapper.countUserCard(userId);
    }

    @Override
    public Integer freezeUser(Integer userId) {
        return managerMapper.freezeUser(userId);
    }

    @Override
    public Integer refreezeUser(Integer userId) {
        return managerMapper.refreezeUser(userId);
    }

    @Override
    public Integer resetAvatar(Integer userId, String updContent) {
        return managerMapper.resetAvatar(userId, updContent);
    }

    @Override
    public Integer resetName(Integer userId, String updContent) {
        return managerMapper.resetName(userId, updContent);
    }

    @Override
    public Integer getAllBankCount() {
        return managerMapper.bankNum();
    }

    @Override
    public List<Bank> getAllBank(Integer anchor, Integer pageSize) {
        return managerMapper.allBank(anchor, pageSize);
    }
    @Override
    public Integer getAllBankCountni() {
        return managerMapper.bankNumni();
    }

    @Override
    public List<Bank> getAllBankni(Integer anchor, Integer pageSize) {
        return managerMapper.allBankni(anchor, pageSize);
    }

    @Override
    public Integer countBankCode(String searchContent) {
        return managerMapper.searchBankCodeNum(searchContent);
    }

    @Override
    public Integer countBankName(String searchContent) {
        return managerMapper.searchBankNameNum(searchContent);
    }

    @Override
    public List<Bank> getBankByCode(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.searchBankCode(searchContent, anchor, pageSize);
    }

    @Override
    public List<Bank> getBankByName(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.searchBankName(searchContent, anchor, pageSize);
    }

    @Override
    public Integer countBankCodeNoImg(String searchContent) {
        return managerMapper.searchBankCodeNumNoImg(searchContent);
    }

    @Override
    public Integer countBankNameNoImg(String searchContent) {
        return managerMapper.searchBankNameNumNoImg(searchContent);
    }

    @Override
    public List<Bank> getBankByCodeNoImg(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.searchBankCodeNoImg(searchContent, anchor, pageSize);
    }

    @Override
    public List<Bank> getBankByNameNoImg(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.searchBankNameNoImg(searchContent, anchor, pageSize);
    }

    @Override
    public Integer changeBankLogo(Integer bankId, String bankImg) {
        return managerMapper.updBankLogo(bankId, bankImg);
    }

//    ------------------------------------------------------------------------------------------------
    @Override
    public Integer getAllFundCount() {
        return managerMapper.getAllFundCount();
    }

    @Override
    public List<Finance> getAllFund(Integer anchor, Integer pageSize) {
        return managerMapper.getAllFund(anchor, pageSize);
    }

    @Override
    public Integer getUserApplyCount() {
        return managerMapper.getUserApplyCount();
    }

    @Override
    public List<FundAddApply> getUserApply(Integer anchor, Integer pageSize) {
        return managerMapper.getUserApply(anchor, pageSize);
    }

    @Override
    public Integer getAllFundCodeCount(String searchContent) {
        return managerMapper.getAllFundCodeCount(searchContent);
    }

    @Override
    public List<Finance> getAllFundCode(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.getAllFundCode(searchContent, anchor, pageSize);
    }

    @Override
    public Integer getUserApplyCodeCount(String searchContent) {
        return managerMapper.getUserApplyCodeCount(searchContent);
    }

    @Override
    public List<FundAddApply> getUserApplyCode(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.getUserApplyCode(searchContent, anchor, pageSize);
    }

    @Override
    public Integer getAllFundNameCount(String searchContent) {
        return managerMapper.getAllFundNameCount(searchContent);
    }

    @Override
    public List<Finance> getAllFundName(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.getAllFundName(searchContent, anchor, pageSize);
    }

    @Override
    public Integer getUserApplyNameCount(String searchContent) {
        return managerMapper.getUserApplyNameCount(searchContent);
    }

    @Override
    public List<FundAddApply> getUserApplyName(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.getUserApplyName(searchContent, anchor, pageSize);
    }

    @Override
    public Integer getAllFund1NumCount(String searchContent) {
        return managerMapper.getAllFund1NumCount(searchContent);
    }

    @Override
    public List<Finance> getAllFund1Num(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.getAllFund1Num(searchContent, anchor, pageSize);
    }

    @Override
    public Integer getUserApply1NumCount(String searchContent) {
        return managerMapper.getUserApply1NumCount(searchContent);
    }

    @Override
    public List<FundAddApply> getUserApply1Num(String searchContent, Integer anchor, Integer pageSize) {
        return managerMapper.getUserApply1Num(searchContent, anchor, pageSize);
    }

    @Override
    public Integer changeNameAllFund(String newName, Integer fundId) {
        return managerMapper.changeNameAllFund(newName, fundId);
    }

    @Override
    public Integer fundExistCheck(String fundCode) {
        return managerMapper.fundExistCheck(fundCode);
    }

    @Override
    public Integer addFundUserApply(String fundName, String fundCode) {
        return managerMapper.addFundUserApply(fundName, fundCode);
    }

    @Override
    public Integer userApplyFinish(String fundCode) {
        return managerMapper.userApplyFinish(fundCode);
    }

    @Override
    public Integer refuseUserApply(Integer applyId) {
        return managerMapper.refuseUserApply(applyId);
    }
}
