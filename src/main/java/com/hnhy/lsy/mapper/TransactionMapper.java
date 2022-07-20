package com.hnhy.lsy.mapper;

import com.hnhy.lsy.entity.FundForRecord;
import com.hnhy.lsy.entity.Transaction;
import com.hnhy.lsy.entity.UserCardsForRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TransactionMapper {
    Integer cardAddUnbindRecordAdd(@Param("userId")Integer userId,
                                   @Param("mainMark")Integer mainMark,
                                   @Param("mainContentId")Integer mainContentId,
                                   @Param("vMark")Integer vMark,
                                   @Param("tdate")Date tdate,
                                   @Param("moneyAmount")Double moneyAmount);
    Integer pMoneyModOther(@Param("userId")Integer userId,
                           @Param("mainMark")Integer mainMark,
                           @Param("vMark")Integer vMark,
                           @Param("objMark")Integer objMark,
                           @Param("MoneyResult")Double MoneyResult,
                           @Param("tdate")Date tdate,
                           @Param("moneyAmount")Double moneyAmount);
    Integer pMoneyModCard(@Param("userId")Integer userId,
                          @Param("mainMark")Integer mainMark,
                          @Param("vMark")Integer vMark,
                          @Param("objMark")Integer objMark,
                          @Param("objContentId")Integer objContentId,
                          @Param("MoneyResult")Double MoneyResult,
                          @Param("tdate")Date tdate,
                          @Param("moneyAmount")Double moneyAmount);

    Integer fundSoldAllOther(@Param("userId") Integer userId,
                             @Param("mainContentId")Integer mainContentId,
                             @Param("tdate")Date tdate,
                             @Param("moneyAmount")Double moneyAmount);
    Integer fundSoldAllPocket(@Param("userId") Integer userId,
                              @Param("mainContentId")Integer mainContentId,
                              @Param("tdate")Date tdate,
                              @Param("MoneyResult")Double MoneyResult,
                              @Param("moneyAmount")Double moneyAmount);
    Integer fundSoldAllCard(@Param("userId") Integer userId,
                            @Param("mainContentId")Integer mainContentId,
                            @Param("objContentId")Integer objContentId,
                            @Param("tdate")Date tdate,
                            @Param("moneyAmount")Double moneyAmount);
    Integer fundSoldPartOther(@Param("userId") Integer userId,
                              @Param("mainContentId") Integer mainContentId,
                              @Param("tdate") Date tdate,
                              @Param("moneyAmount") Double moneyAmount);
    Integer fundSoldPartPocket(@Param("userId") Integer userId,
                              @Param("mainContentId")Integer mainContentId,
                              @Param("tdate")Date tdate,
                              @Param("MoneyResult")Double MoneyResult,
                              @Param("moneyAmount")Double moneyAmount);
    Integer fundSoldPartCard(@Param("userId") Integer userId,
                            @Param("mainContentId")Integer mainContentId,
                            @Param("objContentId")Integer objContentId,
                            @Param("tdate")Date tdate,
                            @Param("moneyAmount")Double moneyAmount);
    Integer fundBuyOther(@Param("userId") Integer userId,
                              @Param("mainContentId") Integer mainContentId,
                              @Param("tdate") Date tdate,
                              @Param("moneyAmount") Double moneyAmount);
    Integer fundBuyPocket(@Param("userId") Integer userId,
                               @Param("mainContentId")Integer mainContentId,
                               @Param("tdate")Date tdate,
                               @Param("MoneyResult")Double MoneyResult,
                               @Param("moneyAmount")Double moneyAmount);
    Integer fundBuyCard(@Param("userId") Integer userId,
                             @Param("mainContentId")Integer mainContentId,
                             @Param("objContentId")Integer objContentId,
                             @Param("tdate")Date tdate,
                             @Param("moneyAmount")Double moneyAmount);
    Integer fundNewOther(@Param("userId") Integer userId,
                         @Param("mainContentId") Integer mainContentId,
                         @Param("tdate") Date tdate,
                         @Param("moneyAmount") Double moneyAmount);
    Integer fundNewPocket(@Param("userId") Integer userId,
                          @Param("mainContentId")Integer mainContentId,
                          @Param("tdate")Date tdate,
                          @Param("MoneyResult")Double MoneyResult,
                          @Param("moneyAmount")Double moneyAmount);
    Integer fundNewCard(@Param("userId") Integer userId,
                        @Param("mainContentId")Integer mainContentId,
                        @Param("objContentId")Integer objContentId,
                        @Param("tdate")Date tdate,
                        @Param("moneyAmount")Double moneyAmount);

    List<Transaction> fundTradeDetail(@Param("userId") Integer userId,
                                      @Param("mainId") Integer mainId);

    UserCardsForRecord getCardInfo(@Param("objId") Integer objId);
    UserCardsForRecord getCardInfo2(@Param("objId") Integer objId);

    List<Transaction> pocketTradeDetail(@Param("userId") Integer userId);
    FundForRecord getFundInfo(@Param("userHoldId") Integer mainId);

    List<Transaction> cardTradeDetail(@Param("userId") Integer userId,
                                      @Param("cardId") Integer cardId);

    Integer transactionAllAllCount(@Param("userId") Integer userId,@Param("beginDate") String beginDate,@Param("endDate")String endDate,@Param("vMarkList") List<Integer> vMarkList);
    List<Transaction> transactionAllAll(@Param("userId") Integer userId,
                                        // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                        @Param("beginDate") String beginDate,@Param("endDate")String endDate,
                                        @Param("vMarkList") List<Integer> vMarkList,
                                        // 页码和每页显示数量
                                        @Param("anchor") Integer anchor, @Param("pageSize") Integer pageSize);

    Integer transactionFundCount(@Param("userId") Integer userId,@Param("beginDate") String beginDate,@Param("endDate")String endDate,@Param("vMarkList") List<Integer> vMarkList);
    List<Transaction> transactionFund(@Param("userId") Integer userId,
                                        // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                        @Param("beginDate") String beginDate,@Param("endDate")String endDate,
                                        @Param("vMarkList") List<Integer> vMarkList,
                                        // 页码和每页显示数量
                                        @Param("anchor") Integer anchor, @Param("pageSize") Integer pageSize);

    Integer transactionPocketCount(@Param("userId") Integer userId,@Param("beginDate") String beginDate,@Param("endDate")String endDate,@Param("vMarkList") List<Integer> vMarkList);
    Integer transactionPocketCountFund(@Param("userId") Integer userId,@Param("beginDate") String beginDate,@Param("endDate")String endDate,@Param("vMarkList") List<Integer> vMarkList);
    List<Transaction> transactionPocket(@Param("userId") Integer userId,
                                        // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                        @Param("beginDate") String beginDate,@Param("endDate")String endDate,
                                        @Param("vMarkList") List<Integer> vMarkList,
                                        // 页码和每页显示数量
                                        @Param("anchor") Integer anchor, @Param("pageSize") Integer pageSize);
    List<Transaction> transactionPocketFund(@Param("userId") Integer userId,
                                        // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                        @Param("beginDate") String beginDate,@Param("endDate")String endDate,
                                        @Param("vMarkList") List<Integer> vMarkList,
                                        // 页码和每页显示数量
                                        @Param("anchor") Integer anchor, @Param("pageSize") Integer pageSize);

    Integer transactionCardCount(@Param("userId") Integer userId,@Param("beginDate") String beginDate,@Param("endDate")String endDate,@Param("vMarkList") List<Integer> vMarkList);
    Integer transactionCardCountFund(@Param("userId") Integer userId,@Param("beginDate") String beginDate,@Param("endDate")String endDate,@Param("vMarkList") List<Integer> vMarkList);
    List<Transaction> transactionCard(@Param("userId") Integer userId,
                                        // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                        @Param("beginDate") String beginDate,@Param("endDate")String endDate,
                                        @Param("vMarkList") List<Integer> vMarkList,
                                        // 页码和每页显示数量
                                        @Param("anchor") Integer anchor, @Param("pageSize") Integer pageSize);
    List<Transaction> transactionCardFund(@Param("userId") Integer userId,
                                      // 前端转成 yyyy-MM-dd HH:mm:ss字符串形式发过来
                                      @Param("beginDate") String beginDate,@Param("endDate")String endDate,
                                      @Param("vMarkList") List<Integer> vMarkList,
                                      // 页码和每页显示数量
                                      @Param("anchor") Integer anchor, @Param("pageSize") Integer pageSize);
    Integer recordDelte(@Param("recordId") Integer recordId);

    Integer recordRecycleCount(@Param("userId") Integer userId);
    List<Transaction> recordRecycle(@Param("userId") Integer userId,
                                        @Param("anchor") Integer anchor, @Param("pageSize") Integer pageSize);
    Integer recycleRecord(@Param("existState") Integer existState,@Param("recordId") List<Integer> recordId);


}
