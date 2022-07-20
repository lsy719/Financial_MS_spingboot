package com.hnhy.lsy.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String account ;
    private String password;
    private String nick_name;
    private String img_url;
    private String phone;
    private Double pocketMoney;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getPocketMoney() {
        return pocketMoney;
    }

    public void setPocketMoney(Double pocketMoney) {
        this.pocketMoney = pocketMoney;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", img_url='" + img_url + '\'' +
                ", phone='" + phone + '\'' +
                ", pocketMoney=" + pocketMoney +
                '}';
    }
}
