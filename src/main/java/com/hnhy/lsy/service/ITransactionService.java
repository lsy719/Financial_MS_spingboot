package com.hnhy.lsy.service;

import com.hnhy.lsy.entity.FundForRecord;
import com.hnhy.lsy.entity.Transaction;
import com.hnhy.lsy.entity.UserCardsForRecord;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.Date;
import java.util.List;

public interface ITransactionService {
    Integer addRecord_cardAdd(Integer userId,
                              Integer mainMark,
                              Integer mainContentId,
                              Integer vMark,
                              Date tdate,
                              Double moneyAmount);
    Integer addRecord_pmoney_other(Integer userId,
                                   Integer mainMark,
                                   Integer vMark,
                                   Integer objMark,
                                   Double MoneyResult,
                                   Date tdate,
                                   Double moneyAmount);
    Integer addRecord_pmoney_card(Integer userId,
                                  Integer mainMark,
                                  Integer vMark,
                                  Integer objMark,
                                  Integer objContentId,
                                  Double MoneyResult,
                                  Date tdate,
                                  Double moneyAmount);

    Integer addRecord_allSold_other(Integer userId,
                                    Integer mainContentId,
                                    Date tdate,
                                    Double moneyAmount);

    Integer addRecord_allSold_pocket(Integer userId,
                                     Integer mainContentId,
                                     Date tdate,
                                     Double MoneyResult,
                                     Double moneyAmount);

    Integer addRecord_allSold_card(Integer userId,
                                   Integer mainContentId,
                                   Integer objContentId,
                                   Date tdate,
                                   Double moneyAmount);

    Integer addRecord_partSold_other(Integer userId,
                                     Integer mainContentId,
                                     Date tdate,
                                     Double moneyAmount);
    Integer addRecord_partSold_pocket(Integer userId,
                                     Integer mainContentId,
                                     Date tdate,
                                     Double MoneyResult,
                                     Double moneyAmount);
    Integer addRecord_partSold_card(Integer userId,
                                   Integer mainContentId,
                                   Integer objContentId,
                                   Date tdate,
                                   Double moneyAmount);
    Integer addRecord_buy_other(Integer userId,
                                     Integer mainContentId,
                                     Date tdate,
                                     Double moneyAmount);
    Integer addRecord_buy_pocket(Integer userId,
                                      Integer mainContentId,
                                      Date tdate,
                                      Double MoneyResult,
                                      Double moneyAmount);
    Integer addRecord_buy_card(Integer userId,
                                    Integer mainContentId,
                                    Integer objContentId,
                                    Date tdate,
                                    Double moneyAmount);

    Integer addRecord_add_other(Integer userId,
                                Integer mainContentId,
                                Date tdate,
                                Double moneyAmount);
    Integer addRecord_add_pocket(Integer userId,
                                 Integer mainContentId,
                                 Date tdate,
                                 Double MoneyResult,
                                 Double moneyAmount);
    Integer addRecord_add_card(Integer userId,
                               Integer mainContentId,
                               Integer objContentId,
                               Date tdate,
                               Double moneyAmount);
    List<Transaction> record_fund(Integer userId, Integer mainId);
    UserCardsForRecord record_getCardInfo1(Integer objId);
    UserCardsForRecord record_getCardInfo2(Integer objId);

    List<Transaction> record_pocket(Integer userId);
    FundForRecord record_getFundInfo(Integer mainId);

    List<Transaction> record_card(Integer userId, Integer cardId);

    Integer myrecord_all_count(Integer userId,String beginDate, String endDate,List<Integer> vMarkList);
    List<Transaction> myrecord_all(Integer userId,
                                   // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                   String beginDate, String endDate,
                                   List<Integer> vMarkList,
                                   // 页码和每页显示数量
                                   Integer anchor, Integer pageSize);

    Integer myrecord_fund_count(Integer userId,String beginDate, String endDate,List<Integer> vMarkList);
    List<Transaction> myrecord_fund(Integer userId,
                                   // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                   String beginDate, String endDate,
                                   List<Integer> vMarkList,
                                   // 页码和每页显示数量
                                   Integer anchor, Integer pageSize);

    Integer myrecord_pocket_count(Integer userId,String beginDate, String endDate,List<Integer> vMarkList);
    Integer myrecord_pocket_count_fund(Integer userId,String beginDate, String endDate,List<Integer> vMarkList);
    List<Transaction> myrecord_pocket(Integer userId,
                                   // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                   String beginDate, String endDate,
                                   List<Integer> vMarkList,
                                   // 页码和每页显示数量
                                   Integer anchor, Integer pageSize);
    List<Transaction> myrecord_pocket_fund(Integer userId,
                                      // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                      String beginDate, String endDate,
                                      List<Integer> vMarkList,
                                      // 页码和每页显示数量
                                      Integer anchor, Integer pageSize);

    Integer myrecord_card_count(Integer userId,String beginDate, String endDate,List<Integer> vMarkList);
    Integer myrecord_card_count_fund(Integer userId,String beginDate, String endDate,List<Integer> vMarkList);
    List<Transaction> myrecord_card(Integer userId,
                                   // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                   String beginDate, String endDate,
                                   List<Integer> vMarkList,
                                   // 页码和每页显示数量
                                   Integer anchor, Integer pageSize);
    List<Transaction> myrecord_card_fund(Integer userId,
                                    // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                    String beginDate, String endDate,
                                    List<Integer> vMarkList,
                                    // 页码和每页显示数量
                                    Integer anchor, Integer pageSize);

    Integer myrecord_del(Integer recordId);

    Integer myrecord_rcc_count(Integer userId);
    List<Transaction> myrecord_rcc(Integer userId,
                                   Integer anchor, Integer pageSize);
    Integer myrecord_rcc_upd(Integer existState, List<Integer> recordId);
}
