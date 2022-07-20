package com.hnhy.lsy.service.impl;

import com.hnhy.lsy.entity.*;
import com.hnhy.lsy.mapper.FinanceMapper;
import com.hnhy.lsy.service.IFinanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FinanceServiceImpl implements IFinanceService {
    @Resource
    private FinanceMapper financeMapper;

    @Override
    public List<UserHold> queryUserHolds(Integer userId) {
        return financeMapper.selectUserHoldByUserId(userId);
    }

    @Override
    public List<FinanceState> querySingelFondState(Integer userId, String financeCode) {
        return financeMapper.selectSingleFinanceState(userId, financeCode);
    }

    @Override
    public Double figureBankBalance(Integer userId) {
        return financeMapper.countBankBalance(userId);
    }

    @Override
    public Integer addFundState(FinanceState financeState) {
        return financeMapper.addFinanceState(financeState);
    }

    @Override
    public Integer modFundStateToday(Integer fsId,Double thenState) {
        return financeMapper.updTodayState(fsId,thenState);
    }

    @Override
    public UserHold reload1Fund(Integer userHoldId) {
        return financeMapper.reloadSingleFund1(userHoldId);
    }

    @Override
    public Integer soldAllMoney0(Integer userId, String financeCode) {
        return financeMapper.soldAllBaseMoney(userId, financeCode);
    }

    @Override
    public Integer soldAllDelState(Integer userId, String financeCode) {
        return financeMapper.soldAllStateDel(userId, financeCode);
    }

    @Override
    public Integer soldPartBaseMoney(Double newMoney, Integer holdId) {
        return financeMapper.soldPartBaseMoney(newMoney, holdId);
    }

    @Override
    public Integer soldPartThenState(Double newState, Integer fsId) {
        return financeMapper.soldPartThenState(newState, fsId);
    }

    @Override
    public Integer buyAnywayBaseMoney(Double newMoney, Integer holdId) {
        return financeMapper.buyBaseMoney(newMoney, holdId);
    }

    @Override
    public List<Finance> getAllFundList() {
        return financeMapper.selectAllFund();
    }

    @Override
    public List<FinanceHold> getHoldFundList(Integer userId) {
        return financeMapper.selectHoldFund(userId);
    }

    @Override
    public List<Finance> getFundByName(String searchName) {
        return financeMapper.selectFundByName(searchName);
    }

    @Override
    public Integer addUserHold(Integer userId, String financeCode, Double baseMoney, Date iniDate) {
        return financeMapper.addUserHold(userId, financeCode, baseMoney, iniDate);
    }

    @Override
    public Integer getFundId(Integer userId, String fundCode) {
        return financeMapper.getFundIdJustAdd(userId, fundCode);
    }

    @Override
    public Integer unIncludeFundAddApply(Integer userId, String fundName, String fundCode, Date applyTime) {
        return financeMapper.addFundAddApply(userId, fundName, fundCode, applyTime);
    }

    @Override
    public List<FundAddApply> checkAddApply(Integer userId) {
        return financeMapper.checkFundAddApply(userId);
    }

    @Override
    public Integer deleteAddApply(Integer applyId) {
        return financeMapper.delFundAddApply(applyId);
    }

    @Override
    public Integer meBought(Integer userId, String fundCode) {
        return financeMapper.checkBoughtbefore(userId, fundCode);
    }

    @Override
    public Integer soModify(Integer userId, String financeCode, Double baseMoney, Date iniDate) {
        return financeMapper.updBaseMoneyAAA(userId, financeCode, baseMoney, iniDate);
    }
}
