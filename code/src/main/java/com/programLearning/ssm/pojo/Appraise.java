package com.programLearning.ssm.pojo;

import java.util.Date;
import java.util.Random;

public class Appraise {
    private int appraiseId;
    private int appointmentId;
    private String mentorUsername;
    private String content;
    private Date createTime;
    private Date updateTime;

    public Appraise( int appointmentId, String mentorUsername, String content) {
        this.appraiseId = new Random().nextInt(89999999)+10000000;
        this.appointmentId = appointmentId;
        this.mentorUsername = mentorUsername;
        this.content = content;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public int getAppraiseId() {
        return appraiseId;
    }

    public void setAppraiseId(int appraiseId) {
        this.appraiseId = appraiseId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMentorUsername() {
        return mentorUsername;
    }

    public void setMentorUsername(String mentorUsername) {
        this.mentorUsername = mentorUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
