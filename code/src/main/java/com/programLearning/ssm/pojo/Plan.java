package com.programLearning.ssm.pojo;

import java.util.Date;
import java.util.Random;

public class Plan {
    private int planId;
    private String languageType;
    private String studentUsername;
    private String selfCondition;
    private String planContent;
    private Date createTime;
    private Date updateTime;

    public Plan(String languageType, String studentUsername,String selfCondition, String planContent) {
        this.planId = new Random().nextInt(89999999)+10000000;
        this.languageType = languageType;
        this.studentUsername = studentUsername;
        this.selfCondition = selfCondition;
        this.planContent = planContent;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getSelfCondition() {
        return selfCondition;
    }

    public void setSelfCondition(String selfCondition) {
        this.selfCondition = selfCondition;
    }

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

}
