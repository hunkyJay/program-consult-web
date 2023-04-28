package com.programLearning.ssm.pojo;

import java.util.Date;
import java.util.Random;

public class AppointmentTemp {

    private Appointment appointment;
    private String mentorName;
    private String studentName;

    public AppointmentTemp(Appointment appointment, String mentorName, String studentName) {
        this.appointment = appointment;
        this.mentorName = mentorName;
        this.studentName = studentName;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

}
