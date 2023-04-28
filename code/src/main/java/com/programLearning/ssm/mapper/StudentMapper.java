package com.programLearning.ssm.mapper;

import com.programLearning.ssm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StudentMapper {
    Student studentSelect(@Param("studentUsername") String studentUsername);
    String studentLogin(@Param("studentUsername") String studentUsername);
    void studentRegister(@Param("studentUsername") String studentUsername, @Param("password") String password,@Param("name") String name, @Param("email") String email);
    void studentUpdate(@Param("studentUsername") String studentUsername,@Param("name")String name,@Param("email") String email);
    List<Plan> checkPlan(@Param("studentUsername") String studentUsername);
    Plan getPlan(@Param("planId") int planId);
    void givePlanFeedback(@Param("feedbackId") int feedbackId, @Param("feedbackUsername") String feedbackUsername, @Param("planId") int planId, @Param("content") String content, @Param("isCheck") int isCheck, @Param("createTime") Date createTime, @Param("updateTime") Date updateTime);
    List<Mentor> checkMentor();
    List<Mentor> checkMentorByKeyword(@Param("keyword") String keyword);
    Mentor getMentor(@Param("mentorUsername") String mentorUsername);
    List<Appraise> checkMentorAppraise(@Param("mentorUsername") String mentorUsername);
    void giveAppraise(@Param("appraiseId") int appraiseId,@Param("appointmentId") int appointmentId,@Param("mentorUsername") String mentorUsername, @Param("content") String content,@Param("createTime") Date createTime, @Param("updateTime") Date updateTime);
    Appointment getAppointment(@Param("appointmentId") int appointmentId);
    List<Appointment> getAllAppointment(@Param("studentUsername") String studentUsername);
    List<Feedback> getAllFeedback(@Param("studentUsername") String studentUsername);
    void makeAppointment(@Param("appointmentId") int appointmentId,@Param("studentUsername") String studentUsername,@Param("mentorUsername") String mentorUsername,@Param("appointmentTime") Date appointmentTime,@Param("status") int status,@Param("createTime") Date createTime, @Param("updateTime") Date updateTime);
    void cancelAppointment(@Param("appointmentId") int appointmentId,@Param("updateTime") Date updateTime);
    void setAppointmentAppraised(@Param("appointmentId") int appointmentId,@Param("updateTime") Date updateTime);
    void addPlan(@Param("planId") int planId,@Param("studentUsername") String studentUsername,@Param("languageType") String languageType,@Param("selfCondition") String selfCondition,@Param("planContent") String planContent, @Param("createTime") Date createTime, @Param("updateTime") Date updateTime);
    void saveImg(@Param("studentUsername") String studentUsername,@Param("img") String img);
}
