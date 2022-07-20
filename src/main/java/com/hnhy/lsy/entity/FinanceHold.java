package com.hnhy.lsy.entity;

public class FinanceHold {
    private Integer fundHoldId; //userHold表id
    private Integer userId;
    private Integer financeId;//id
    private String financeName;//name
    private String financeCode;

    public Integer getFundHoldId() {
        return fundHoldId;
    }

    public void setFundHoldId(Integer fundHoldId) {
        this.fundHoldId = fundHoldId;
    }

    public Integer getFinanceId() {
        return financeId;
    }

    public void setFinanceId(Integer financeId) {
        this.financeId = financeId;
    }

    public String getFinanceName() {
        return financeName;
    }

    public void setFinanceName(String financeName) {
        this.financeName = financeName;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "FinanceHold{" +
                "fundHoldId=" + fundHoldId +
                ", userId=" + userId +
                ", financeId=" + financeId +
                ", financeName='" + financeName + '\'' +
                ", financeCode='" + financeCode + '\'' +
                '}';
    }
}
