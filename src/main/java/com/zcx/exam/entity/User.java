package com.zcx.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User extends Object{
    /**
     * 用户ID，唯一标识一个用户
     */
    private Integer id;

    /**
     * 用户名，用户登录时使用
     */
    private String username;

    /**
     * 密码，用户登录时使用，应加密存储
     */
    private String password;

    /**
     * 用户状态，表示用户账户的状态，如激活、锁定等
     */
    private String status;

    /**
     * 盐值，用于密码加密，增加密码安全性
     */
    private String salt;

    /**
     * 性别，用户的性别信息，可选填写
     */
    private String sex;

    /**
     * 角色，用户在系统中的角色，决定用户的权限
     */
    private String role;

    /**
     * 手机号，用户的联系方式，用于接收重要通知
     */
    private String mobile;

    /**
     * 创建时间，记录用户账户创建的时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    /**
     * 创建者，记录创建用户账户的操作者
     */
    private String createBy;

    /**
     * 更新时间，记录用户账户最后一次更新的时间
     */
    private Date updateDate;

    /**
     * 更新者，记录最后一次更新用户账户信息的操作者
     */
    private String updateBy;

    /**
     * 备注，额外的信息备注，用于记录特殊说明
     */
    private String remark;

    public User(Integer id, String username, String password, String status, String salt, String sex, String role, String mobile, Date createDate, String createBy, Date updateDate, String updateBy, String remark) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.status = status;
        this.salt = salt;
        this.sex = sex;
        this.role = role;
        this.mobile = mobile;
        this.createDate = createDate;
        this.createBy = createBy;
        this.updateDate = updateDate;
        this.updateBy = updateBy;
        this.remark = remark;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}