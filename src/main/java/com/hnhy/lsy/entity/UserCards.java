package com.hnhy.lsy.entity;

public class UserCards {
    private int cardId;
    private int userId;
    private String cardKey;
    private String cardName;
    private String cardNote;
    private Double cardMoney;

//    private String bankCode;

    private Bank bank;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
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

    public void setCardName(String cardNme) {
        this.cardName = cardNme;
    }

    public String getCardNote() {
        return cardNote;
    }

    public void setCardNote(String cardNote) {
        this.cardNote = cardNote;
    }

    public Double getCardMoney() {
        return cardMoney;
    }

    public void setCardMoney(Double cardMoney) {
        this.cardMoney = cardMoney;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "UserCards{" +
                "cardId=" + cardId +
                ", userId=" + userId +
                ", cardKey='" + cardKey + '\'' +
                ", cardName='" + cardName + '\'' +
                ", cardNote='" + cardNote + '\'' +
                ", cardMoney=" + cardMoney +
                ", bank=" + bank +
                '}';
    }
}
