package com.programLearning.ssm.service.impl;

import com.programLearning.ssm.mapper.MentorMapper;
import com.programLearning.ssm.pojo.*;
import com.programLearning.ssm.service.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MentorServiceImpl implements MentorService {
    @Autowired
    MentorMapper mentorMapper;

    @Override
    public boolean mentorRegister(String mentorUsername, String password, String name, String email) {
        Mentor mentor = mentorMapper.mentorSelect(mentorUsername);
        if (mentor != null){
            return false;
        }
        try {
            String md5 = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
            mentorMapper.mentorRegister(mentorUsername,md5,name,email);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean mentorLogin(String mentorUsername, String password) {
        String truePassword = mentorMapper.mentorLogin(mentorUsername);
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
    public boolean updateMentorInfo(String mentorUsername, String name,String email, String introduction) {
        try {
            mentorMapper.mentorUpdate(mentorUsername,name, email,introduction);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Appointment> checkReceivedAppointment(String mentorUsername) {
        List<Appointment> appointments = mentorMapper.getAllAppointment(mentorUsername);
        return appointments;
    }

    @Override
    public List<Appointment> checkAcceptedAppointment(String mentorUsername) {
        List<Appointment> appointments = mentorMapper.getAppointmentByStatus(mentorUsername, 1);
        return appointments;
    }

    @Override
    public List<Plan> checkStudentPlan(String studentUsername) {
        List<Plan> plans = mentorMapper.checkPlan(studentUsername);
        return plans;
    }

    @Override
    public boolean confirmAppointment(int appointmentId) {
        try {
            int confirmStatus = 1;
            Date date = new Date();
            mentorMapper.appointmentUpdate(appointmentId, confirmStatus, date);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean refuseAppointment(int appointmentId) {
        try {
            int refuseStatus = 4;
            Date date = new Date();
            mentorMapper.appointmentUpdate(appointmentId, refuseStatus, date);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean completeAppointment(int appointmentId) {
        try {
            int completeStatus = 2;
            Date date = new Date();
            mentorMapper.appointmentUpdate(appointmentId, completeStatus, date);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean addMeetingUrl(int appointmentId, String url) {
        try {
            mentorMapper.addMeetingUrl(appointmentId, url);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public List<Appraise> checkAppraise(String mentorUsername) {
        List<Appraise> appraiseList = mentorMapper.checkAppraise(mentorUsername);
        return appraiseList;
    }

    @Override
    public Mentor getMentor(String mentorUsername) {
        return mentorMapper.mentorSelect(mentorUsername);
    }

    @Override
    public Student getStudent(String studentUsername) {
        Student student = mentorMapper.studentSelect(studentUsername);
        return student;
    }

    @Override
    public void saveImg(String mentorUsername,String img) {
        mentorMapper.saveImg(mentorUsername,img);
    }
}
