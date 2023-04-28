package com.programLearning.ssm.service.impl;


import com.programLearning.ssm.mapper.AdminMapper;
import com.programLearning.ssm.pojo.*;
import com.programLearning.ssm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;


import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public boolean doLogin(String username, String password) {
        String truePassword = adminMapper.doLogin(username);
        System.out.println(truePassword);
        String md5 = "";
        try {
            md5 = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
            if(md5.equals(truePassword)){
                return true;
            }else {
                return false;
            }
        } catch (UnsupportedEncodingException e) {
            return false;
        }

    }

    @Override
    public int getNumOfFeedback() {
        return adminMapper.getNumOfFeedback();
    }

    @Override
    public int getNumOfAppointment() {
        return adminMapper.getNumOfAppointment();
    }

    @Override
    public int getNumOfMentor() {
        return adminMapper.getNumOfMentor();
    }

    @Override
    public int getNumOfStudent() {
        return adminMapper.getNumOfStudent();
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return adminMapper.getAllAppointment();
    }

    @Override
    public Mentor getMentor(String mentorUsername) {
        return adminMapper.getMentor(mentorUsername);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return adminMapper.getAllFeedback();
    }

    @Override
    public List<Student> getAllStudent() {
        return adminMapper.getAllStudent();
    }

    @Override
    public List<Mentor> getAllMentor() {
        return adminMapper.getAllMentor();
    }

    @Override
    public List<Appraise> getAllAppraiseByMenterId(Integer mentorId) {
        return adminMapper.getAllAppraiseByMenterId(mentorId);
    }

    @Override
    public List<Appointment> getAllAppointmentByStudentId(Integer studentId) {
        return adminMapper.getAllAppointmentByStudentId(studentId);
    }

    @Override
    public List<Plan> getAllPlan() {
        return adminMapper.getAllPlan();
    }

    @Override
    public Feedback getFeedback(Integer feedbackId) {
        return adminMapper. getFeedback(feedbackId);
    }

    @Override
    public void checkFeedback(Integer feedbackId) {
        adminMapper.updateFeedbackState(feedbackId,1);
    }

    @Override
    public Plan getPlan(Integer planId) {
        return adminMapper.getPlan(planId);
    }

    @Override
    public Integer getPlanLatestFeedback(Integer planId) {
        return adminMapper.getPlanLatestFeedback(planId);
    }

    @Override
    public void updatePlanContent(Integer planId, String planContent) {
        adminMapper.updatePlanContent( planId,  planContent);
    }

    @Override
    public void changeFeedback(Integer feedbackId) {
        adminMapper.updateFeedbackState(feedbackId,2);
    }

    @Override
    public Appraise getAppraise(Integer appraiseId) {
        return adminMapper.getAppraise( appraiseId);
    }
//
//    @Override
//    public List<Feedback> getAllFeedbacks() {
//        return adminMapper.getAllFeedbacks();
//    }
//
//    @Override
//    public Feedback checkFeedback(int feedbackId) {
//        adminMapper.updateFeedbackState(feedbackId);
//        return adminMapper.selectFeedbackById(feedbackId);
//    }
//
//    @Override
//    public List<Appointment> getAllAppointments() {
//        return adminMapper.getAllAppointments();
//    }
//
//    @Override
//    public Appointment checkAppointment(Integer appointmentId) {
//        return adminMapper.selectAppointmentById(appointmentId);
//    }
//
//    @Override
//    public List<Plan> getAllPlans() {
//        return adminMapper.getAllPlans();
//    }
//
//    @Override
//    public Plan checkPlan(Integer planId) {
//        return adminMapper.selectPlanById(planId);
//    }
}
