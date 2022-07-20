package com.hnhy.lsy.entity;

public class FundForRecord {
    private String fundName;
    private String fundCode;

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Override
    public String toString() {
        return "FundForRecord{" +
                "fundName='" + fundName + '\'' +
                ", fundCode='" + fundCode + '\'' +
                '}';
    }
}
