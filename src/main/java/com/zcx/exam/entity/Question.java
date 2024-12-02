package com.zcx.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Question {
    private Integer id;

    private String questionType;//问题类型

    private String questionDesc;//问题描述

    private String questionStatus;//问题状态

    private String questionPic;//问题截图
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;//问题反馈时间

    private String createBy;//问题反馈人

    private String contactInfo;//联系方式

    private String isDel;

    public Question(Integer id, String questionType, String questionDesc, String questionStatus, Date createTime, String createBy, String contactInfo, String isDel,String questionPic) {
        this.id = id;
        this.questionType = questionType;
        this.questionDesc = questionDesc;
        this.questionStatus = questionStatus;
        this.createTime = createTime;
        this.createBy = createBy;
        this.contactInfo = contactInfo;
        this.isDel = isDel;
        this.questionPic = questionPic;
    }

    public String getQuestionPic() {
        return questionPic;
    }

    public void setQuestionPic(String questionPic) {
        this.questionPic = questionPic;
    }

    public Question() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType == null ? null : questionType.trim();
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc == null ? null : questionDesc.trim();
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus == null ? null : questionStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo == null ? null : contactInfo.trim();
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel == null ? null : isDel.trim();
    }
}