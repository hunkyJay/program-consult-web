package com.programLearning.ssm.mapper;

import com.programLearning.ssm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    String doLogin(@Param("account") String account);
    int getNumOfFeedback();
    int getNumOfAppointment();
    int getNumOfMentor();
    int getNumOfStudent();
    List<Appointment> getAllAppointment();
    Mentor getMentor(@Param("mentorUsername") String mentorUsername);
    List<Feedback> getAllFeedback();
    List<Student> getAllStudent();
    List<Mentor> getAllMentor();

    List<Appraise> getAllAppraiseByMenterId(@Param("mentorId") Integer mentorId);
    List<Appointment> getAllAppointmentByStudentId(@Param("studentId") Integer studentId);

    List<Plan> getAllPlan();

    Feedback getFeedback(@Param("feedbackId") Integer feedbackId);

    void updateFeedbackState(@Param("feedbackId") Integer feedbackId, @Param("state") Integer state);

    Plan getPlan(@Param("planId")Integer planId);

    Integer getPlanLatestFeedback(Integer planId);

    void updatePlanContent(@Param("planId") Integer planId,@Param("planContent") String planContent);

    Appraise getAppraise(Integer appraiseId);

//    List<Feedback> getAllFeedbacks();
//
//    Feedback selectFeedbackById(@Param("feedbackId") int feedbackId);
//
//    void updateFeedbackState(@Param("feedbackId") int feedbackId);
//
//    List<Appointment> getAllAppointments();
//
//    Appointment selectAppointmentById(@Param("appointmentId") Integer appointmentId);
//
//    List<Plan> getAllPlans();
//
//    Plan selectPlanById(@Param("planId") Integer planId);


}
