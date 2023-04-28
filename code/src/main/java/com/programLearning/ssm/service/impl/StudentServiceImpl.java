package com.programLearning.ssm.service.impl;

import com.programLearning.ssm.mapper.AdminMapper;
import com.programLearning.ssm.mapper.StudentMapper;
import com.programLearning.ssm.pojo.*;
import com.programLearning.ssm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student getStudent(String studentUsername){
        return studentMapper.studentSelect(studentUsername);
    }

    @Override
    public boolean studentRegister(String studentUsername, String password, String name, String email) {
        Student student = studentMapper.studentSelect(studentUsername);
        if (student != null){
            return false;
        }
        try {
            String md5 = DigestUtils.md5DigestAsHex(password.getBytes("utf-8"));
            studentMapper.studentRegister(studentUsername,md5,name,email);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean studentLogin(String studentUsername, String password) {
        String truePassword = studentMapper.studentLogin(studentUsername);
        //System.out.println(truePassword);
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
    public boolean updateStuInfo(String studentUsername, String name, String email) {
        try {
            studentMapper.studentUpdate(studentUsername,name,email);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void acquireJavaPlanPlan(String studentUsername,String JavaSE,String JavaWeb,String MySQL,String SpringMVC,String Mybatis,String SpringBoot,String Linux,String JVM,String SpringCloud,String JavaMiddleware) {
        List<Integer> base = new ArrayList<>();
        StringBuilder selfCondition = new StringBuilder();
        StringBuilder recommendation = new StringBuilder();
        if (JavaSE!=null){
            selfCondition.append("JavaSE ");
            base.add(1);
        }

        if (JavaWeb!=null){
            selfCondition.append("JavaWeb ");
            base.add(2);
        }
        if (MySQL!=null){
            selfCondition.append("MySQL ");
            base.add(3);
        }
        if (SpringMVC!=null){
            selfCondition.append("SpringMVC ");
            base.add(4);
        }
        if (Mybatis!=null){
            selfCondition.append("Mybatis ");
            base.add(5);
        }
        if (SpringBoot!=null){
            selfCondition.append("SpringBoot ");
            base.add(6);
        }
        if (Linux!=null){
            selfCondition.append("Linux ");
            base.add(7);
        }
        if (JVM!=null){
            selfCondition.append("JVM ");
            base.add(8);
        }
        if (SpringCloud!=null){
            selfCondition.append("SpringCloud ");
            base.add(9);
        }
        if (JavaMiddleware!=null){
            selfCondition.append("JavaMiddleware ");
            base.add(10);
        }
        if (base.size()==0){
            recommendation.append("You can study from JavaSE");
            Plan plan = new Plan("Java",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
            return;
        }
        if (base.size()==10){
            recommendation.append("You are proficient in Java, the system doesn't find any further recommendation. You can contact with mentor.");
            Plan plan = new Plan("Java",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
            return ;
        }
        Collections.sort(base);
        int baseLength = base.size();
        if(base.get(baseLength-1)!=10){
            recommendation.append("You can study some new knowledge in ");
            recommendation.append(javaReflection(base.get(baseLength-1)+1));
            recommendation.append(" areas. ");
        }
        int count = 0;
        if (baseLength == 1){
            int currentBase = base.get(0);
            for (int i = 1;i<=2;i++){
                if ((currentBase-i)>1){
                    if (count == 0){
                        recommendation.append("You can also supplement some knowledge in ");
                        recommendation.append(javaReflection(currentBase-i));
                        recommendation.append(" areas");
                        count++;
                    }else{
                        recommendation.append(" and ");
                        recommendation.append(javaReflection(currentBase-i));
                        recommendation.append(" areas.");
                        count++;
                    }
                }
            }
            Plan plan = new Plan("Java",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
            return;
        }

        for (int i = baseLength-1;i>0;i--){
            if (count==2){
                break;
            }
            if ( (base.get(i)-1) != base.get(i-1)){
                if (count == 0){
                    recommendation.append("You can also supplement some knowledge in ");
                    recommendation.append(javaReflection(base.get(i)-1));
                    recommendation.append(" areas");
                    count++;
                }else{
                    recommendation.append(" and ");
                    recommendation.append(javaReflection(base.get(i)-1));
                    recommendation.append(" areas.");
                    count++;
                }
            }
        }
        Plan plan = new Plan("Java",studentUsername,selfCondition.toString(),recommendation.toString());
        studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
    }

    @Override
    public void acquirePythonPlan(String studentUsername, String PythonBasic, String PythonLinux, String Database, String PythonWebFrontEnd, String PythonWebBackEnd, String DataAnalytics, String ArtificialIntelligence) {
        List<Integer> base = new ArrayList<>();
        StringBuilder selfCondition = new StringBuilder();
        StringBuilder recommendation = new StringBuilder();
        if (PythonBasic!=null){
            selfCondition.append("Python-Basics ");
            base.add(1);
        }

        if (PythonLinux!=null){
            selfCondition.append("Python-Linux ");
            base.add(2);
        }
        if (Database!=null){
            selfCondition.append("Database ");
            base.add(3);
        }
        if (PythonWebFrontEnd!=null){
            selfCondition.append("Python-Web-Front-End ");
            base.add(4);
        }
        if (PythonWebBackEnd!=null){
            selfCondition.append("Python-Web-Back-End ");
            base.add(5);
        }
        if (DataAnalytics!=null){
            selfCondition.append("Data-Analytics ");
            base.add(6);
        }
        if (ArtificialIntelligence!=null){
            selfCondition.append("Artificial-Intelligence ");
            base.add(7);
        }
        if (base.size()==0){
            recommendation.append("You can study from Python Basic");
            Plan plan = new Plan("Python",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
            return;
        }
        if (base.size()==7){
            recommendation.append("You are proficient in Python, the system doesn't find any further recommendation. You can contact with mentor.");
            Plan plan = new Plan("Python",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
            return;
        }
        Collections.sort(base);
        int baseLength = base.size();
        if(base.get(baseLength-1)!=7){
            recommendation.append("You can study some new knowledge in ");
            recommendation.append(pythonReflection(base.get(baseLength-1)+1));
            recommendation.append(" areas. ");
        }
        int count = 0;
        if (baseLength == 1){
            int currentBase = base.get(0);
            for (int i = 1;i<=2;i++){
                if ((currentBase-i)>1){
                    if (count == 0){
                        recommendation.append("You can also supplement some knowledge in ");
                        recommendation.append(pythonReflection(currentBase-i));
                        recommendation.append(" areas");
                        count++;
                    }else{
                        recommendation.append(" and ");
                        recommendation.append(pythonReflection(currentBase-i));
                        recommendation.append(" areas.");
                        count++;
                    }
                }
            }
            Plan plan = new Plan("Python",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
            return;
        }

        for (int i = baseLength-1;i>0;i--){
            if (count==2){
                break;
            }
            if ( (base.get(i)-1) != base.get(i-1)){
                if (count == 0){
                    recommendation.append("You can also supplement some knowledge in ");
                    recommendation.append(pythonReflection(base.get(i)-1));
                    recommendation.append(" areas");
                    count++;
                }else{
                    recommendation.append(" and ");
                    recommendation.append(pythonReflection(base.get(i)-1));
                    recommendation.append(" areas.");
                    count++;
                }
            }
        }
        Plan plan = new Plan("Python",studentUsername,selfCondition.toString(),recommendation.toString());
        studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
    }

    @Override
    public void acquireCPlan(String studentUsername, String CBasic, String CProficiency, String CPlusBasic, String CPlusProficiency, String Windows, String CLinux) {
        List<Integer> base = new ArrayList<>();
        StringBuilder selfCondition = new StringBuilder();
        StringBuilder recommendation = new StringBuilder();
        if (CBasic!=null){
            selfCondition.append("C-Language-Basic ");
            base.add(1);
        }

        if (CProficiency!=null){
            selfCondition.append("C-Language-Proficiency ");
            base.add(2);
        }
        if (CPlusBasic!=null){
            selfCondition.append("C++ Basic ");
            base.add(3);
        }
        if (CPlusProficiency!=null){
            selfCondition.append("Python-Web-Front-End ");
            base.add(4);
        }
        if (Windows!=null){
            selfCondition.append("Windows-Senior-Engineer ");
            base.add(5);
        }
        if (CLinux!=null){
            selfCondition.append("Linux-system-application-development ");
            base.add(6);
        }

        if (base.size()==0){
            recommendation.append("You can study from C Language Basic");
            Plan plan = new Plan("C Language",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());;
            return;
        }
        if (base.size()==6){
            recommendation.append("You are proficient in C Language, the system doesn't find any further recommendation. You can contact with mentor.");
            Plan plan = new Plan("C Language",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());;
            return;
        }
        Collections.sort(base);
        int baseLength = base.size();
        if(base.get(baseLength-1)!=6){
            recommendation.append("You can study some new knowledge in ");
            recommendation.append(CReflection(base.get(baseLength-1)+1));
            recommendation.append(" areas. ");
        }
        int count = 0;
        if (baseLength == 1){
            int currentBase = base.get(0);
            for (int i = 1;i<=2;i++){
                if ((currentBase-i)>1){
                    if (count == 0){
                        recommendation.append("You can also supplement some knowledge in ");
                        recommendation.append(CReflection(currentBase-i));
                        recommendation.append(" areas");
                        count++;
                    }else{
                        recommendation.append(" and ");
                        recommendation.append(CReflection(currentBase-i));
                        recommendation.append(" areas.");
                        count++;
                    }
                }
            }
            Plan plan = new Plan("C Language",studentUsername,selfCondition.toString(),recommendation.toString());
            studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
            return;
        }

        for (int i = baseLength-1;i>0;i--){
            if (count==2){
                break;
            }
            if ( (base.get(i)-1) != base.get(i-1)){
                if (count == 0){
                    recommendation.append("You can also supplement some knowledge in ");
                    recommendation.append(CReflection(base.get(i)-1));
                    recommendation.append(" areas");
                    count++;
                }else{
                    recommendation.append(" and ");
                    recommendation.append(CReflection(base.get(i)-1));
                    recommendation.append(" areas.");
                    count++;
                }
            }
        }
        Plan plan = new Plan("C Language",studentUsername,selfCondition.toString(),recommendation.toString());
        studentMapper.addPlan(plan.getPlanId(),plan.getStudentUsername(),plan.getLanguageType(),plan.getSelfCondition(),plan.getPlanContent(),plan.getCreateTime(),plan.getUpdateTime());
    }

    @Override
    public Plan getPlan(int planId) {
        return studentMapper.getPlan(planId);
    }

    @Override
    public List<Plan> checkPlan(String studentUsername) {
        List<Plan> plans = studentMapper.checkPlan(studentUsername);
        return plans;
    }


    @Override
    public boolean givePlanFeedback(String studentUsername, int planId, String content) {
        try{
            Feedback feedback = new Feedback(studentUsername,planId,content);
            studentMapper.givePlanFeedback(feedback.getFeedbackId(),feedback.getFeedbackUsername(),feedback.getPlanId(),feedback.getContent(),feedback.getIsCheck(),feedback.getCreateTime(),feedback.getUpdateTime());
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public List<Mentor> checkMentor() {
        List<Mentor> mentors = studentMapper.checkMentor();
        return mentors;
    }

    @Override
    public List<Mentor> checkMentorByKeyword(String keyword) {
        List<Mentor> mentors = studentMapper.checkMentorByKeyword(keyword);
        System.out.println("mentors:"+mentors.size());
        return mentors;
    }

    @Override
    public Mentor getMentor(String mentorUsername) {
        Mentor mentor = studentMapper.getMentor(mentorUsername);
        return mentor;
    }

    @Override
    public List<Appraise> checkMentorAppraise(String mentorUsername) {
        List<Appraise> appraises = studentMapper.checkMentorAppraise(mentorUsername);
        return appraises;
    }

    @Override
    public boolean makeAppointment(String studentUsername, String mentorUsername, String appointmentTime) {
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            Date date= simpleDateFormat.parse(appointmentTime);
            Appointment appointment = new Appointment(studentUsername,mentorUsername,date);
            studentMapper.makeAppointment(appointment.getAppointmentId(),appointment.getStudentUsername(),appointment.getMentorUsername(),appointment.getAppointmentTime(),appointment.getStatus(),appointment.getCreateTime(),appointment.getUpdateTime());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Appointment getAppointment(int appointmentId) {
        Appointment appointment = studentMapper.getAppointment(appointmentId);
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointment(String studentUsername) {
        List<Appointment> appointments = studentMapper.getAllAppointment(studentUsername);
        return appointments;
    }

    @Override
    public List<Feedback> getAllFeedback(String studentUsername) {
        List<Feedback> feedbacks = studentMapper.getAllFeedback(studentUsername);
        return feedbacks;
    }


    @Override
    public boolean cancelAppointment(int appointmentId) {
        try{
            Date date = new Date();
            studentMapper.cancelAppointment(appointmentId,date);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean giveAppraise(int appointmentId,String mentorUsername, String content) {
        try{
            Appraise appraise = new Appraise(appointmentId,mentorUsername,content);
            studentMapper.giveAppraise(appraise.getAppraiseId(),appraise.getAppointmentId(),appraise.getMentorUsername(),appraise.getContent(),appraise.getCreateTime(),appraise.getUpdateTime());
            Date date = new Date();
            studentMapper.setAppointmentAppraised(appointmentId,date);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void saveImg(String studentUsername,String img) {
        studentMapper.saveImg(studentUsername,img);
    }

    public String javaReflection(int num){
        if (num == 1){
            return "JavaSE";
        }else if(num == 2){
            return "JavaWeb";
        }else if(num ==3 ){
            return "MySQL";
        }else if(num ==4 ){
            return "SpringMVC";
        }else if(num ==5 ){
            return "Mybatis";
        }else if(num ==6 ){
            return "Spring boot";
        }else if(num ==7 ){
            return "Linux";
        }else if(num ==8 ){
            return "JVM";
        }else if(num ==9 ){
            return "Spring Cloud";
        }else {
            return "JavaMiddleware";
        }
    }

    public String pythonReflection(int num){
        if (num == 1){
            return "Python Basics";
        }else if(num == 2){
            return "Python Linux";
        }else if(num == 3){
            return "Database";
        }else if(num == 4){
            return "Python Web Front End";
        }else if(num == 5){
            return "Python Web Back End";
        }else if(num == 6) {
            return "Data Analytics";
        }else {
            return "Artificial Intelligence";
        }
    }

    public String CReflection(int num){
        if (num == 1){
            return "C Language Basic";
        }else if(num == 2){
            return "C Language Proficiency";
        }else if(num == 3){
            return "C++ Basic";
        }else if(num == 4){
            return "C++ Proficiency";
        }else if(num == 5){
            return "Windows Senior Engineer";
        }else {
            return "Linux system application development";
        }
    }
}
