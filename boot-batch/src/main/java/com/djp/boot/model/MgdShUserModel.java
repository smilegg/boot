package com.djp.boot.model;

import java.util.Date;

public class MgdShUserModel {
    private Long id;

    private Long mchId;

    private Long pid;

    private String pids;

    private String account;

    private String password;

    private String name;

    private Integer isMch;// 是否是商户

    private String telNo; // 用户手机号

    private String addTime;

    private String updateTime;

    private Integer shUserStatus;

    private Integer userLevel;

    private Integer loginStatus;

    private Date loginTime;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMchId() {
        return mchId;
    }

    public void setMchId(Long mchId) {
        this.mchId = mchId;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsMch() {
        return isMch;
    }

    public void setIsMch(Integer isMch) {
        this.isMch = isMch;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getShUserStatus() {
        return shUserStatus;
    }

    public void setShUserStatus(Integer shUserStatus) {
        this.shUserStatus = shUserStatus;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
