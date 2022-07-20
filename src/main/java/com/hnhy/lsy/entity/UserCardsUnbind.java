package com.hnhy.lsy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UserCardsUnbind {
    private Integer id;
    private Integer userId;
    private String cardKey;
    private String cardName;
    private Double disbindMoney;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date disbindDate;
    private Integer agoId;

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

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Double getDisbindMoney() {
        return disbindMoney;
    }

    public void setDisbindMoney(Double disbindMoney) {
        this.disbindMoney = disbindMoney;
    }

    public Date getDisbindDate() {
        return disbindDate;
    }

    public void setDisbindDate(Date disbindDate) {
        this.disbindDate = disbindDate;
    }

    public Integer getAgoId() {
        return agoId;
    }

    public void setAgoId(Integer agoId) {
        this.agoId = agoId;
    }

    @Override
    public String toString() {
        return "UserCardsUnbind{" +
                "id=" + id +
                ", userId=" + userId +
                ", cardKey='" + cardKey + '\'' +
                ", cardName='" + cardName + '\'' +
                ", disbindMoney=" + disbindMoney +
                ", disbindDate=" + disbindDate +
                ", agoId=" + agoId +
                '}';
    }
}
