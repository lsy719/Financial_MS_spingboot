package com.hnhy.lsy.entity;

public class TransactionMain {
    private Integer tMId;
    private String tMName;

    public Integer gettMId() {
        return tMId;
    }

    public void settMId(Integer tMId) {
        this.tMId = tMId;
    }

    public String gettMName() {
        return tMName;
    }

    public void settMName(String tMName) {
        this.tMName = tMName;
    }

    @Override
    public String toString() {
        return "TransactionMain{" +
                "tMId=" + tMId +
                ", tMName='" + tMName + '\'' +
                '}';
    }
}
