package com.hnhy.lsy.service;

import com.hnhy.lsy.entity.CardNote;
import com.hnhy.lsy.entity.UserCards;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;
import java.util.List;

public interface ICardsService {
//    获取用户银行卡
    List<UserCards> queryCardsById(Integer id);
//    获取卡备注提示
    List<CardNote> queryCardNotes();
//    修改卡备注
    Integer changeCardNote(Integer cardId, Integer userId, String cardNote);
//    单张卡详情页
    UserCards getOneCard(Integer cardId, Integer userId);
//    修改余额
    Integer modifyMoney(Double cardMoney, Integer cardId, Integer userId);

//    检查是否已经绑定
    Integer checkHasCard(Integer userId,String cardKey);
//    检查银行是否已经存储
    Integer checkHasBank(String bankCode);
//    添加银行卡
    Integer addUserCard(Integer userId,
                        String cardKey,
                        String cardName,
                        Double cardMoney,
                        String bankCode);
//    添加银行信息（为后期管理做准备）
    Integer addBankInfo(String bankCode,
                        String bankName,
                        String bankImg);

//    检查账号是否已被使用
    Integer checkHasAccount(String account);
    Integer registerUser(String account,
                         String password,
                         String nick_name,
                         String img_url,
                         String phone,
                         Double pocketMoney);
//    向解绑银行卡记录表添加
    Integer addunbindCard(Integer userId,
                          String cardKey,
                          String cardName,
                          Double disbindMoney,
                          Date disbindDate,
                          Integer cardId);
//    删除银行卡
    Integer delcard(Integer cardId);
//    获取刚添加的银行卡ID
    Integer getIdCardJustAdd(Integer userId, String cardKey);
//    检查卡是不是以前解绑过
    Integer checkHasUnbundCard(Integer userId, String cardKey);
//    解绑过了，就修改下金额和日期就行
    Integer updUnbindCard(Double disbindMoney,Date disbindDate,Integer cardId, Integer userId, String cardKey);

    Integer checkHasPhone(String phone);
}
