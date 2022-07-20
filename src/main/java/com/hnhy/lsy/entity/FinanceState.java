package com.hnhy.lsy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class FinanceState implements Serializable {
    private Integer fsId;//id
    private Integer userId;
    private String financeCode;


    private Double thenState;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updDate;

    public Integer getFsId() {
        return fsId;
    }

    public void setFsId(Integer fsId) {
        this.fsId = fsId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode;
    }

    public Double getThenState() {
        return thenState;
    }

    public void setThenState(Double thenState) {
        this.thenState = thenState;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }

    @Override
    public String toString() {
        return "FinanceState{" +
                "fsId=" + fsId +
                ", userId=" + userId +
                ", financeCode='" + financeCode + '\'' +
                ", thenState=" + thenState +
                ", updDate=" + updDate +
                '}';
    }
}
