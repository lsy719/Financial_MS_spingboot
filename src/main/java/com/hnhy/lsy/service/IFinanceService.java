package com.hnhy.lsy.service;

import com.hnhy.lsy.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface IFinanceService{
//    查找出当前用户所有持有基金
    List<UserHold> queryUserHolds(Integer userId);
//    查出此项基金的收益情况
    List<FinanceState> querySingelFondState(Integer userId, String financeCode);
//    查询当前用户银行卡总余额
    Double figureBankBalance(Integer userId);
//    新增基金记录
    Integer addFundState(FinanceState financeState);
//    修改今日记录
    Integer modFundStateToday(Integer fsId,Double thenState);
//    重载当前单条基金
    UserHold reload1Fund(Integer userHoldId);

//    基金全部卖出
//    baseMOney置0
    Integer soldAllMoney0(Integer userId, String financeCode);
//    删除state
    Integer soldAllDelState(Integer userId, String financeCode);

//  修改baseMoney
    Integer soldPartBaseMoney(Double newMoney, Integer holdId);
//    修改最新thenState
    Integer soldPartThenState(Double newState, Integer fsId);

//    修改baseMOney
    Integer buyAnywayBaseMoney(Double newMoney, Integer holdId);

//    查出所有finance
    List<Finance> getAllFundList();
//    查找已持有基金
    List<FinanceHold> getHoldFundList(Integer userId);
//    根据名称查基金
    List<Finance> getFundByName(String searchName);
//    添加用户持有
    Integer addUserHold(Integer userId,
                        String financeCode,
                        Double baseMoney,
                        Date iniDate);
//    查询刚添加的基金的Id
    Integer getFundId(Integer userId,
                      String fundCode);
//    未收录基金添加申请
    Integer unIncludeFundAddApply(Integer userId,
                                  String fundName,
                                  String fundCode,
                                  Date applyTime);
//    查看我的申请记录
    List<FundAddApply> checkAddApply(Integer userId);
//    删除申请记录
    Integer deleteAddApply(Integer applyId);

//    我以前买过
    Integer meBought(Integer userId, String fundCode);
//    改下baseMoney和日期就行了
    Integer soModify(Integer userId,
                     String financeCode,
                     Double baseMoney,
                     Date iniDate);
}
