package com.hnhy.lsy.service;

import com.hnhy.lsy.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IManagerService {
    Manager managerLoginFirst(String account,String password);
    Manager managerAfterLogin(String account,String password,Integer id);

    Integer adminPwChangeOld(String pwOld, Integer id);
    Integer adminPwChange(String pwNew, Integer id);

    List<Manager> adminManagerList();
    Integer adminStateChange(Integer newState, Integer id);

    Integer accountExistCheck(String account);
    Integer addManager(String account, String password, String createTime);

    Integer adminGetUserCount();
    List<UserForAdmin> adminGetUser(Integer anchor, Integer pageSize);
    Integer searchByIdCount(String searchContent);
    List<UserForAdmin> searchById(String searchContent, Integer anchor, Integer pageSize);
    Integer searchByAccCount(String searchContent);
    List<UserForAdmin> searchByAcc(String searchContent, Integer anchor, Integer pageSize);
    Integer searchByNameCount(String searchContent);
    List<UserForAdmin> searchByName(String searchContent, Integer anchor, Integer pageSize);

    Integer countUserFund(Integer userId);
    Integer countUserCard(Integer userId);

    Integer freezeUser(Integer userId);
    Integer refreezeUser(Integer userId);
    Integer resetAvatar(Integer userId, String updContent);
    Integer resetName(Integer userId, String updContent);

    Integer getAllBankCount();
    List<Bank> getAllBank(Integer anchor, Integer pageSize);
    Integer getAllBankCountni();
    List<Bank> getAllBankni(Integer anchor, Integer pageSize);

    Integer countBankCode(String searchContent);
    Integer countBankName(String searchContent);
    List<Bank> getBankByCode(String searchContent, Integer anchor, Integer pageSize);
    List<Bank> getBankByName(String searchContent, Integer anchor, Integer pageSize);

    Integer countBankCodeNoImg(String searchContent);
    Integer countBankNameNoImg(String searchContent);
    List<Bank> getBankByCodeNoImg(String searchContent, Integer anchor, Integer pageSize);
    List<Bank> getBankByNameNoImg(String searchContent, Integer anchor, Integer pageSize);

    Integer changeBankLogo(Integer bankId, String bankImg);

    // 1。找出全部
    Integer getAllFundCount();
    List<Finance> getAllFund(Integer anchor,Integer pageSize);
    Integer getUserApplyCount();
    List<FundAddApply> getUserApply(Integer anchor,Integer pageSize);

    // 2.按代码查
    Integer getAllFundCodeCount(String searchContent);
    List<Finance> getAllFundCode(String searchContent,Integer anchor,Integer pageSize);
    Integer getUserApplyCodeCount(String searchContent);
    List<FundAddApply> getUserApplyCode(String searchContent,Integer anchor,Integer pageSize);

    // 3.按名称查
    Integer getAllFundNameCount(String searchContent);
    List<Finance> getAllFundName(String searchContent,Integer anchor,Integer pageSize);
    Integer getUserApplyNameCount(String searchContent);
    List<FundAddApply> getUserApplyName(String searchContent,Integer anchor,Integer pageSize);

    // 4.按代码首数字查
    Integer getAllFund1NumCount(String searchContent);
    List<Finance> getAllFund1Num(String searchContent,Integer anchor,Integer pageSize);
    Integer getUserApply1NumCount(String searchContent);
    List<FundAddApply> getUserApply1Num(String searchContent,Integer anchor,Integer pageSize);

    Integer changeNameAllFund(String newName, Integer fundId);

    Integer fundExistCheck(String fundCode);
    Integer addFundUserApply(String fundName, String fundCode);
    Integer userApplyFinish(String fundCode);

    Integer refuseUserApply(Integer applyId);
}
