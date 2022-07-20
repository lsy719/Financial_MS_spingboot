package com.hnhy.lsy.mapper;

import com.hnhy.lsy.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface FinanceMapper {
    List<UserHold> selectUserHoldByUserId(Integer userId);
    List<FinanceState> selectSingleFinanceState(@Param("userId") Integer userId,
                                                @Param("financeCode") String financeCode);
    Double countBankBalance(@Param("userId") Integer userId);
//    控制层接口记得挨个set
    Integer addFinanceState(FinanceState financeState);
    Integer updTodayState(@Param("fsId") Integer fsId,
                          @Param("thenState") Double thenState);
    UserHold reloadSingleFund1(Integer userHoldId);

    Integer soldAllBaseMoney(@Param("userId") Integer userId,
                             @Param("financeCode") String financeCode);

    Integer soldAllStateDel(@Param("userId") Integer userId,
                             @Param("financeCode") String financeCode);

    Integer soldPartBaseMoney(@Param("newMoney") Double newMoney,
                              @Param("holdId") Integer holdId);

    Integer soldPartThenState(@Param("newState") Double newState,
                              @Param("fsId") Integer fsId);

    Integer buyBaseMoney(@Param("newMoney") Double newMoney,
                         @Param("holdId") Integer holdId);

    List<Finance> selectAllFund();
    List<FinanceHold> selectHoldFund(@Param("userId") Integer userId);
    List<Finance> selectFundByName(@Param("searchName")String searchName);
    Integer addUserHold(@Param("userId") Integer userId,
                        @Param("financeCode") String financeCode,
                        @Param("baseMoney") Double baseMoney,
                        @Param("iniDate") Date iniDate);
    Integer getFundIdJustAdd(@Param("userId") Integer userId,
                             @Param("fundCode") String fundCode);

    Integer addFundAddApply(@Param("userId") Integer userId,
                            @Param("fundName") String fundName,
                            @Param("fundCode") String fundCode,
                            @Param("applyTime") Date applyTime);
    List<FundAddApply> checkFundAddApply(@Param("userId") Integer userId);
    Integer delFundAddApply(@Param("applyId") Integer applyId);

    Integer checkBoughtbefore(@Param("userId") Integer userId,
                              @Param("fundCode") String fundCode);

    Integer updBaseMoneyAAA(@Param("userId") Integer userId,
                            @Param("financeCode") String financeCode,
                            @Param("baseMoney") Double baseMoney,
                            @Param("iniDate") Date iniDate);
}
