package com.hnhy.lsy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Transaction {
    private Integer id;
    private Integer userId;

//    private Integer mainMark;
    private TransactionMain transactionMain;
    private Integer mainContentId;

//    用来设置基金、银行卡名称和基金代码、银行卡号
    private String mainInfoName;
    private String mainInfoCode;

//    private Integer vMark;
    private TransactionVerb transactionVerb;

//    private Integer objMark;
    private TransactionObj transactionObj;
    private Integer objContentId;

    //    用来设置基金、银行卡名称和基金代码、银行卡号
    private String objInfoName;
    private String objInfoCode;

    private Double moneyResult;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date tdate;
    private Double moneyAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public TransactionMain getTransactionMain() {
        return transactionMain;
    }

    public void setTransactionMain(TransactionMain transactionMain) {
        this.transactionMain = transactionMain;
    }

    public Integer getMainContentId() {
        return mainContentId;
    }

    public void setMainContentId(Integer mainContentId) {
        this.mainContentId = mainContentId;
    }

    public TransactionVerb getTransactionVerb() {
        return transactionVerb;
    }

    public void setTransactionVerb(TransactionVerb transactionVerb) {
        this.transactionVerb = transactionVerb;
    }

    public TransactionObj getTransactionObj() {
        return transactionObj;
    }

    public void setTransactionObj(TransactionObj transactionObj) {
        this.transactionObj = transactionObj;
    }

    public Integer getObjContentId() {
        return objContentId;
    }

    public void setObjContentId(Integer objContentId) {
        this.objContentId = objContentId;
    }

    public Double getMoneyResult() {
        return moneyResult;
    }

    public void setMoneyResult(Double moneyResult) {
        this.moneyResult = moneyResult;
    }

    public Date getTdate() {
        return tdate;
    }

    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public Double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getMainInfoName() {
        return mainInfoName;
    }

    public void setMainInfoName(String mainInfoName) {
        this.mainInfoName = mainInfoName;
    }

    public String getMainInfoCode() {
        return mainInfoCode;
    }

    public void setMainInfoCode(String mainInfoCode) {
        this.mainInfoCode = mainInfoCode;
    }

    public String getObjInfoName() {
        return objInfoName;
    }

    public void setObjInfoName(String objInfoName) {
        this.objInfoName = objInfoName;
    }

    public String getObjInfoCode() {
        return objInfoCode;
    }

    public void setObjInfoCode(String objInfoCode) {
        this.objInfoCode = objInfoCode;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", userId=" + userId +
                ", transactionMain=" + transactionMain +
                ", mainContentId=" + mainContentId +
                ", mainInfoName='" + mainInfoName + '\'' +
                ", mainInfoCode='" + mainInfoCode + '\'' +
                ", transactionVerb=" + transactionVerb +
                ", transactionObj=" + transactionObj +
                ", objContentId=" + objContentId +
                ", objInfoName='" + objInfoName + '\'' +
                ", objInfoCode='" + objInfoCode + '\'' +
                ", moneyResult=" + moneyResult +
                ", tdate=" + tdate +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
