package com.hnhy.lsy.mapper;

import com.hnhy.lsy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User find(User user);
    User secondCfm(User user);
    Integer changeImg(@Param("id") Integer id, @Param("img_url") String img_url);
    User getUserById(Integer id);
    Integer userAccEcho(@Param("id") Integer id, @Param("account") String accout);
    Integer changeAcc(@Param("id") Integer id, @Param("account") String accout);
    Integer changeUn(@Param("id") Integer id, @Param("newName") String newName);
    Integer changePn(@Param("id") Integer id, @Param("phone") String phone);
    Integer cfmOldPassword(@Param("id") Integer id, @Param("passwordOld")String passwordOld);
    Integer changePassword(@Param("id") Integer id, @Param("passwordNew")String passwordNew);
    Integer bankCardsCount(@Param("id") Integer id);
    Integer changePocketMoney(@Param("newMoney") Double newMoney, @Param("userId") Integer userId);
    Integer checkPhoneOccur(@Param("userId")Integer userId, @Param("newPhone") String newPhone);
}
