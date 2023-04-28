package com.programLearning.ssm.service;

import com.programLearning.ssm.pojo.*;

import java.util.List;

public interface MentorService {

    boolean mentorRegister (String mentorUsername, String password, String name, String introduction);
    boolean mentorLogin (String mentorUsername,String password);
    boolean updateMentorInfo(String mentorUsername,String name, String email, String introduction);
    List<Appointment> checkReceivedAppointment(String mentorUsername);
    List<Appointment> checkAcceptedAppointment(String mentorUsername);
    List<Plan> checkStudentPlan(String studentUsername);
    boolean confirmAppointment(int appointmentId);
    boolean refuseAppointment(int appointmentId);
    boolean completeAppointment(int appointmentId);
    boolean addMeetingUrl (int appointmentId, String url);
    List<Appraise> checkAppraise(String mentorUsername);
    Mentor getMentor(String mentorUsername);
    Student getStudent(String studentUsername);
    void saveImg(String studentUsername,String img);


}
