package com.hnhy.lsy.entity;

public class UserCardsForRecord {
    private String cardKey;
    private String cardName;

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

    @Override
    public String toString() {
        return "UserCardsForRecord{" +
                "cardKey='" + cardKey + '\'' +
                ", cardName='" + cardName + '\'' +
                '}';
    }
}
