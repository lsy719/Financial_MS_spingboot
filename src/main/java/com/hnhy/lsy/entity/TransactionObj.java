package com.hnhy.lsy.entity;

public class TransactionObj {
    private Integer tOId;
    private String tOName;

    public Integer gettOId() {
        return tOId;
    }

    public void settOId(Integer tOId) {
        this.tOId = tOId;
    }

    public String gettMOName() {
        return tOName;
    }

    public void settMOName(String tMOName) {
        this.tOName = tMOName;
    }

    @Override
    public String toString() {
        return "TransactionObj{" +
                "tOId=" + tOId +
                ", tOName='" + tOName + '\'' +
                '}';
    }
}
