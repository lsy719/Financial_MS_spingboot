package com.hnhy.lsy.service.impl;

import com.hnhy.lsy.entity.FundForRecord;
import com.hnhy.lsy.entity.Transaction;
import com.hnhy.lsy.entity.UserCardsForRecord;
import com.hnhy.lsy.mapper.TransactionMapper;
import com.hnhy.lsy.service.ITransactionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {
    @Resource
    TransactionMapper transactionMapper;

    @Override
    public Integer addRecord_cardAdd(Integer userId, Integer mainMark, Integer mainContentId, Integer vMark, Date tdate, Double moneyAmount) {
        return transactionMapper.cardAddUnbindRecordAdd(userId, mainMark, mainContentId, vMark, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_pmoney_other(Integer userId, Integer mainMark, Integer vMark, Integer objMark, Double MoneyResult, Date tdate, Double moneyAmount) {
        return transactionMapper.pMoneyModOther(userId, mainMark, vMark, objMark, MoneyResult, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_pmoney_card(Integer userId, Integer mainMark, Integer vMark, Integer objMark, Integer objContentId, Double MoneyResult, Date tdate, Double moneyAmount) {
        return transactionMapper.pMoneyModCard(userId, mainMark, vMark, objMark, objContentId, MoneyResult, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_allSold_other(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundSoldAllOther(userId, mainContentId, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_allSold_pocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount) {
        return transactionMapper.fundSoldAllPocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
    }

    @Override
    public Integer addRecord_allSold_card(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundSoldAllCard(userId, mainContentId, objContentId, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_partSold_other(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundSoldPartOther(userId, mainContentId, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_partSold_pocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount) {
        return transactionMapper.fundSoldPartPocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
    }

    @Override
    public Integer addRecord_partSold_card(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundSoldPartCard(userId, mainContentId, objContentId, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_buy_other(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundBuyOther(userId, mainContentId, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_buy_pocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount) {
        return transactionMapper.fundBuyPocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
    }

    @Override
    public Integer addRecord_buy_card(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundBuyCard(userId, mainContentId, objContentId, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_add_other(Integer userId, Integer mainContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundNewOther(userId, mainContentId, tdate, moneyAmount);
    }

    @Override
    public Integer addRecord_add_pocket(Integer userId, Integer mainContentId, Date tdate, Double MoneyResult, Double moneyAmount) {
        return transactionMapper.fundNewPocket(userId, mainContentId, tdate, MoneyResult, moneyAmount);
    }

    @Override
    public Integer addRecord_add_card(Integer userId, Integer mainContentId, Integer objContentId, Date tdate, Double moneyAmount) {
        return transactionMapper.fundNewCard(userId, mainContentId, objContentId, tdate, moneyAmount);
    }

    @Override
    public List<Transaction> record_fund(Integer userId, Integer mainId) {
        return transactionMapper.fundTradeDetail(userId, mainId);
    }

    @Override
    public UserCardsForRecord record_getCardInfo1(Integer objId) {
        return transactionMapper.getCardInfo(objId);
    }

    @Override
    public UserCardsForRecord record_getCardInfo2(Integer objId) {
        return transactionMapper.getCardInfo2(objId);
    }

    @Override
    public List<Transaction> record_pocket(Integer userId) {
        return transactionMapper.pocketTradeDetail(userId);
    }

    @Override
    public FundForRecord record_getFundInfo(Integer mainId) {
        return transactionMapper.getFundInfo(mainId);
    }

    @Override
    public List<Transaction> record_card(Integer userId, Integer cardId) {
        return transactionMapper.cardTradeDetail(userId, cardId);
    }

    @Override
    public Integer myrecord_all_count(Integer userId, String beginDate, String endDate, List<Integer> vMarkList) {
        return transactionMapper.transactionAllAllCount(userId, beginDate, endDate, vMarkList);
    }

    @Override
    public List<Transaction> myrecord_all(Integer userId, String beginDate, String endDate, List<Integer> vMarkList, Integer anchor, Integer pageSize) {
        return transactionMapper.transactionAllAll(userId, beginDate, endDate, vMarkList, anchor, pageSize);
    }

    @Override
    public Integer myrecord_fund_count(Integer userId, String beginDate, String endDate, List<Integer> vMarkList) {
        return transactionMapper.transactionFundCount(userId, beginDate, endDate, vMarkList);
    }

    @Override
    public List<Transaction> myrecord_fund(Integer userId, String beginDate, String endDate, List<Integer> vMarkList, Integer anchor, Integer pageSize) {
        return transactionMapper.transactionFund(userId, beginDate, endDate, vMarkList, anchor, pageSize);
    }

    @Override
    public Integer myrecord_pocket_count(Integer userId, String beginDate, String endDate, List<Integer> vMarkList) {
        return transactionMapper.transactionPocketCount(userId, beginDate, endDate, vMarkList);
    }

    @Override
    public Integer myrecord_pocket_count_fund(Integer userId, String beginDate, String endDate, List<Integer> vMarkList) {
        return transactionMapper.transactionPocketCountFund(userId, beginDate, endDate, vMarkList);
    }

    @Override
    public List<Transaction> myrecord_pocket(Integer userId, String beginDate, String endDate, List<Integer> vMarkList, Integer anchor, Integer pageSize) {
        return transactionMapper.transactionPocket(userId, beginDate, endDate, vMarkList, anchor, pageSize);
    }

    @Override
    public List<Transaction> myrecord_pocket_fund(Integer userId, String beginDate, String endDate, List<Integer> vMarkList, Integer anchor, Integer pageSize) {
        return transactionMapper.transactionPocketFund(userId, beginDate, endDate, vMarkList, anchor, pageSize);
    }

    @Override
    public Integer myrecord_card_count(Integer userId, String beginDate, String endDate, List<Integer> vMarkList) {
        return transactionMapper.transactionCardCount(userId, beginDate, endDate, vMarkList);
    }

    @Override
    public Integer myrecord_card_count_fund(Integer userId, String beginDate, String endDate, List<Integer> vMarkList) {
        return transactionMapper.transactionCardCountFund(userId, beginDate, endDate, vMarkList);
    }

    @Override
    public List<Transaction> myrecord_card(Integer userId, String beginDate, String endDate, List<Integer> vMarkList, Integer anchor, Integer pageSize) {
        return transactionMapper.transactionCard(userId, beginDate, endDate, vMarkList, anchor, pageSize);
    }

    @Override
    public List<Transaction> myrecord_card_fund(Integer userId, String beginDate, String endDate, List<Integer> vMarkList, Integer anchor, Integer pageSize) {
        return transactionMapper.transactionCardFund(userId, beginDate, endDate, vMarkList, anchor, pageSize);
    }

    @Override
    public Integer myrecord_del(Integer recordId) {
        return transactionMapper.recordDelte(recordId);
    }

    @Override
    public Integer myrecord_rcc_count(Integer userId) {
        return transactionMapper.recordRecycleCount(userId);
    }

    @Override
    public List<Transaction> myrecord_rcc(Integer userId, Integer anchor, Integer pageSize) {
        return transactionMapper.recordRecycle(userId, anchor, pageSize);
    }


    @Override
    public Integer myrecord_rcc_upd(Integer existState, List<Integer> recordId) {
        return transactionMapper.recycleRecord(existState, recordId);
    }


}
