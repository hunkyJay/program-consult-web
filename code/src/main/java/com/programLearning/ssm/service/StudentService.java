package com.programLearning.ssm.service;

import com.programLearning.ssm.pojo.*;

import java.util.Date;
import java.util.List;

public interface StudentService {
    Student getStudent(String studentUsername);
    boolean studentRegister (String studentUsername,String password,String name,String email);
    boolean studentLogin (String studentUsername,String password);
    boolean updateStuInfo(String studentUsername,String name,String email);
    void acquireJavaPlanPlan(String studentUsername,String JavaSE,String JavaWeb,String MySQL,String SpringMVC,String Mybatis,String SpringBoot,String Linux,String JVM,String SpringCloud,String JavaMiddleware);
    void acquirePythonPlan(String studentUsername,String PythonBasic,String PythonLinux,String Database,String PythonWebFrontEnd,String PythonWebBackEnd,String DataAnalytics,String ArtificialIntelligence);
    void acquireCPlan(String studentUsername,String CBasic,String CProficiency,String CPlusBasic,String CPlusProficiency,String Windows,String CLinux);
    Plan getPlan(int planId);
    List<Plan> checkPlan(String studentUsername);
    boolean givePlanFeedback(String studentUsername, int planId, String content);
    List<Mentor> checkMentor();
    List<Mentor> checkMentorByKeyword(String keyword);
    Mentor getMentor(String mentorUsername);
    List<Appraise> checkMentorAppraise(String mentorUsername);
    boolean makeAppointment(String studentUsername, String mentorUsername, String appointmentTime);
    Appointment getAppointment(int appointmentId);
    List<Appointment> getAllAppointment(String studentUsername);
    List<Feedback> getAllFeedback(String studentUsername);
    boolean cancelAppointment(int appointmentId);
    boolean giveAppraise(int appointmentId, String mentorUsername,String content);
    void saveImg(String studentUsername,String img);

}
