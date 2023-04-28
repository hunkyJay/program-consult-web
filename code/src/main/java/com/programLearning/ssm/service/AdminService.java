package com.programLearning.ssm.service;


import com.programLearning.ssm.pojo.*;

import java.util.List;

public interface AdminService {
    boolean doLogin(String username,String password);

    int getNumOfFeedback();
    int getNumOfAppointment();
    int getNumOfMentor();
    int getNumOfStudent();

    List<Appointment> getAllAppointment();
    Mentor getMentor(String mentorUsername);
    List<Feedback> getAllFeedback();
    List<Student> getAllStudent();
    List<Mentor> getAllMentor();

    List<Appraise> getAllAppraiseByMenterId(Integer mentorId);
    List<Appointment> getAllAppointmentByStudentId(Integer studentId);

    List<Plan> getAllPlan();

    Feedback getFeedback(Integer feedbackId);

    void checkFeedback(Integer feedbackId);

    Plan getPlan(Integer planId);

    Integer getPlanLatestFeedback(Integer planId);

    void updatePlanContent(Integer planId, String planContent);

    void changeFeedback(Integer feedbackId);

    Appraise getAppraise(Integer appraiseId);

//    List<Feedback> getAllFeedbacks();
//
//    Feedback checkFeedback(int feedbackId);
//
//    List<Appointment> getAllAppointments();
//
//    Appointment checkAppointment(Integer appointmentId);
//
//    List<Plan> getAllPlans();
//
//    Plan checkPlan(Integer planId);
}
