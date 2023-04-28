package com.programLearning.ssm.pojo;

import java.util.Date;
import java.util.Random;

public class Feedback {

    private int feedbackId;
    private String feedbackUsername;
    private int planId;
    private String content;
    private int isCheck;
    private Date createTime;
    private Date updateTime;

    public Feedback(){}

    public Feedback(String feedbackUsername, int planId, String content) {
        this.feedbackId = new Random().nextInt(89999999)+10000000;
        this.feedbackUsername = feedbackUsername;
        this.planId = planId;
        this.content = content;
        this.isCheck = 0;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackUsername() {
        return feedbackUsername;
    }

    public void setFeedbackUsername(String feedbackUsername) {
        this.feedbackUsername = feedbackUsername;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
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



}
