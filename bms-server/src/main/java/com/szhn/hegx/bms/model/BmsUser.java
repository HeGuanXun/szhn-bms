package com.szhn.hegx.bms.model;


import java.io.Serializable;
import java.util.List;

/**
 * <p>图书馆里系统用户</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/
public class BmsUser implements Serializable {

    private Integer userId;

    private String userName;

    private String passWord;

    private Integer type;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BmsUser() {
    }

}
