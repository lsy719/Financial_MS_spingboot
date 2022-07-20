package com.hnhy.lsy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UserHold implements Serializable {
    private Integer userHoldId;//id
    private Integer userId;

//    private String financeCode;
    private Finance finance;

    private Double baseMoney;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date iniDate;

    public Integer getUserHoldId() {
        return userHoldId;
    }

    public void setUserHoldId(Integer userHoldId) {
        this.userHoldId = userHoldId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Finance getFinance() {
        return finance;
    }

    public void setFinance(Finance finance) {
        this.finance = finance;
    }

    public Double getBaseMoney() {
        return baseMoney;
    }

    public void setBaseMoney(Double baseMoney) {
        this.baseMoney = baseMoney;
    }

    public Date getIniDate() {
        return iniDate;
    }

    public void setIniDate(Date iniDate) {
        this.iniDate = iniDate;
    }

    @Override
    public String toString() {
        return "UserHold{" +
                "userHoldId=" + userHoldId +
                ", userId=" + userId +
                ", finance=" + finance +
                ", baseMoney=" + baseMoney +
                ", iniDate=" + iniDate +
                '}';
    }
}
