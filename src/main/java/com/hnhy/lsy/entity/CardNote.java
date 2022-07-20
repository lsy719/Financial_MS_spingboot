package com.hnhy.lsy.entity;

import java.io.Serializable;

public class CardNote implements Serializable {
    private int cardNoteId;//数据库中为id
    private String cardNote;

    public int getCardNoteId() {
        return cardNoteId;
    }

    public void setCardNoteId(int cardNoteId) {
        this.cardNoteId = cardNoteId;
    }

    public String getCardNote() {
        return cardNote;
    }

    public void setCardNote(String cardNote) {
        this.cardNote = cardNote;
    }

    @Override
    public String toString() {
        return "CardNote{" +
                "cardNoteId=" + cardNoteId +
                ", cardNote='" + cardNote + '\'' +
                '}';
    }
}
