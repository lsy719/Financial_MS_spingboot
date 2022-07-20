package com.hnhy.lsy.entity;

public class TransactionVerb {
    private Integer tVId;
    private String tVname;

    public Integer gettVId() {
        return tVId;
    }

    public void settVId(Integer tVId) {
        this.tVId = tVId;
    }

    public String gettVname() {
        return tVname;
    }

    public void settVname(String tVname) {
        this.tVname = tVname;
    }

    @Override
    public String toString() {
        return "TransactionVerb{" +
                "tVId=" + tVId +
                ", tVname='" + tVname + '\'' +
                '}';
    }
}
