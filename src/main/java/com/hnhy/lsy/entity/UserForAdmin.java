package com.hnhy.lsy.entity;

public class UserForAdmin {
    private Integer id;
    private String account ;
    private String nick_name;
    private String img_url;
    private Integer userState;
    private Integer fundNum;
    private Integer cardNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    public Integer getFundNum() {
        return fundNum;
    }

    public void setFundNum(Integer fundNum) {
        this.fundNum = fundNum;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    @Override
    public String toString() {
        return "UserForAdmin{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", img_url='" + img_url + '\'' +
                ", userState=" + userState +
                ", fundNum=" + fundNum +
                ", cardNum=" + cardNum +
                '}';
    }
}
