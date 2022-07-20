package com.hnhy.lsy.mapper;

import com.hnhy.lsy.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

@Mapper
public interface ManagerMapper {
    Manager firstLogin(@Param("account") String account, @Param("password") String password);
    Manager afterLogin(@Param("account") String account, @Param("password") String password, @Param("id")Integer id);

    Integer changeAdminPasswprdOldPw(@Param("pwOld") String pwOld, @Param("id") Integer id);
    Integer changeAdminPassword(@Param("pwNew") String pwNew, @Param("id") Integer id);
    List<Manager> getManagerList();

    Integer changeAdminState(@Param("newState") Integer newState, @Param("id") Integer id);

    Integer accountExist(@Param("account") String account);
    Integer addAdmin(@Param("account") String account,
                     @Param("password")String password,
                     @Param("createTime")String createTime);

    Integer getAlluserCount();
    List<UserForAdmin> getAllUser(@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer searchUserByidCount(@Param("searchContent")String searchContent);
    List<UserForAdmin> searchUserByid(@Param("searchContent")String searchContent, @Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer searchUserByAccCount(@Param("searchContent")String searchContent);
    List<UserForAdmin> searchUserByAcc(@Param("searchContent")String searchContent, @Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer searchUserByNameCount(@Param("searchContent")String searchContent);
    List<UserForAdmin> searchUserByName(@Param("searchContent")String searchContent, @Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    Integer countUserFund(@Param("userId") Integer userId);
    Integer countUserCard(@Param("userId") Integer userId);

    Integer freezeUser(@Param("userId")Integer userId);
    Integer refreezeUser(@Param("userId")Integer userId);
    Integer resetAvatar(@Param("userId")Integer userId, @Param("updContent")String updContent);
    Integer resetName(@Param("userId")Integer userId, @Param("updContent")String updContent);

    Integer bankNum();
    List<Bank> allBank(@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer bankNumni();
    List<Bank> allBankni(@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    Integer searchBankCodeNum(@Param("searchContent")String searchContent);
    Integer searchBankNameNum(@Param("searchContent")String searchContent);
    List<Bank> searchBankCode(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    List<Bank> searchBankName(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    Integer searchBankCodeNumNoImg(@Param("searchContent")String searchContent);
    Integer searchBankNameNumNoImg(@Param("searchContent")String searchContent);
    List<Bank> searchBankCodeNoImg(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    List<Bank> searchBankNameNoImg(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    Integer updBankLogo(@Param("bankId")Integer bankId,@Param("bankImg")String bankImg);

    // 1。找出全部
    Integer getAllFundCount();
    List<Finance> getAllFund(@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer getUserApplyCount();
    List<FundAddApply> getUserApply(@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    // 2.按代码查
    Integer getAllFundCodeCount(@Param("searchContent")String searchContent);
    List<Finance> getAllFundCode(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer getUserApplyCodeCount(@Param("searchContent")String searchContent);
    List<FundAddApply> getUserApplyCode(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    // 3.按名称查
    Integer getAllFundNameCount(@Param("searchContent")String searchContent);
    List<Finance> getAllFundName(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer getUserApplyNameCount(@Param("searchContent")String searchContent);
    List<FundAddApply> getUserApplyName(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    // 4.按代码首数字查
    Integer getAllFund1NumCount(@Param("searchContent")String searchContent);
    List<Finance> getAllFund1Num(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);
    Integer getUserApply1NumCount(@Param("searchContent")String searchContent);
    List<FundAddApply> getUserApply1Num(@Param("searchContent")String searchContent,@Param("anchor")Integer anchor,@Param("pageSize")Integer pageSize);

    Integer changeNameAllFund(@Param("newName") String newName, @Param("fundId")Integer fundId);

    Integer fundExistCheck(@Param("fundCode") String fundCode);
    Integer addFundUserApply(@Param("fundName") String fundName, @Param("fundCode") String fundCode);
    Integer userApplyFinish(@Param("fundCode") String fundCode);

    Integer refuseUserApply(@Param("applyId")Integer applyId);
}
