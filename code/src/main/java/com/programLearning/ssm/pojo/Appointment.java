package com.programLearning.ssm.pojo;

import java.util.Date;
import java.util.Random;

public class Appointment {

    private int appointmentId;
    private String studentUsername;
    private String mentorUsername;
    private Date appointmentTime;
    private String meetingUrl;
    private int status;
    private Date createTime;
    private Date updateTime;

    public Appointment(){

    }

    public Appointment(String studentUsername,String mentorUsername, Date appointmentTime){
        this.appointmentId = new Random().nextInt(89999999)+10000000;
        this.studentUsername = studentUsername;
        this.mentorUsername = mentorUsername;
        this.appointmentTime = appointmentTime;
        this.status = 0;
        this.createTime = new Date();
        this.updateTime = new Date();
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getMentorUsername() {
        return mentorUsername;
    }

    public void setMentorUsername(String mentorUsername) {
        this.mentorUsername = mentorUsername;
    }

    public Date getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Date appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getMeetingUrl() {
        return meetingUrl;
    }

    public void setMeetingUrl(String meetingUrl) {
        this.meetingUrl = meetingUrl;
    }

    public int getStatus() {
        return status;
    }


    public void setStatus(int status) {
        this.status = status;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date  createTime) {
        this.createTime = createTime;
    }

    public Date  getUpdateTime () {
        return updateTime ;
    }

    public void setUpdateTime (Date updateTime ) {
        this.updateTime  = updateTime;
    }

}
