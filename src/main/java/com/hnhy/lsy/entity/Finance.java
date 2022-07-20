package com.hnhy.lsy.entity;

import java.io.Serializable;

public class Finance implements Serializable{
    private Integer financeId;//id
    private String financeName;//name
    private String financeCode;

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



    @Override
    public String toString() {
        return "Finance{" +
                "financeId=" + financeId +
                ", financeName='" + financeName + '\'' +
                ", financeCode='" + financeCode + '\'' +
                '}';
    }
}
