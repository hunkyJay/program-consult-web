package com.programLearning.ssm.controller;

import com.programLearning.ssm.pojo.*;
import com.programLearning.ssm.service.AdminService;
import com.programLearning.ssm.service.util.RandonCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static com.programLearning.ssm.service.util.RandonCodeGenerator.RANDOMCODEKEY;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminServiceImpl;

    @RequestMapping("/doLogin")
    public String doLogin(String account,String password,String verifyCode, HttpSession session){
        boolean result = adminServiceImpl.doLogin(account,password);
        boolean verify = verifyCode.equalsIgnoreCase((String)session.getAttribute(RANDOMCODEKEY));
        if(result&&verify){
            session.setAttribute("User_name", account);
            return "redirect:/admin/";
        }else{
            return "admin_login";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "admin_login";
    }

    @RequestMapping("/verify")
    public void verify(HttpServletRequest request, HttpServletResponse response){
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandonCodeGenerator randomValidateCode = new RandonCodeGenerator();
        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/")
    public String index(Model model){
        int numOfStudent=adminServiceImpl.getNumOfStudent();
        int numOfMentor=adminServiceImpl.getNumOfMentor();
        int numOfFeedback=adminServiceImpl.getNumOfFeedback();
        int numOfAppointment=adminServiceImpl.getNumOfAppointment();
        model.addAttribute("numOfStudent",numOfStudent);
        model.addAttribute("numOfMentor",numOfMentor);
        model.addAttribute("numOfFeedback",numOfFeedback);
        model.addAttribute("numOfAppointment",numOfAppointment);
        return "admin_homepage";
    }

    @RequestMapping("/appointments")
    public String getAllAppointment(Model model){
        List<Appointment> appointments = adminServiceImpl.getAllAppointment();
        model.addAttribute("appointments",appointments);
        return "admin_appointment_list";
    }

    @RequestMapping("/feedbacks")
    public String getAllFeedback(Model model){
        List<Feedback> feedbacks = adminServiceImpl.getAllFeedback();

        model.addAttribute("feedbacks",feedbacks);
        return "admin_feedback_list";
    }

    @RequestMapping("/mentors")
    public String getAllMentor(Model model){
        List<Mentor> mentors = adminServiceImpl.getAllMentor();

        model.addAttribute("mentors",mentors);
        return "admin_mentor_list";
    }

    @RequestMapping("/students")
    public String getAllStudent(Model model){
        List<Student> students = adminServiceImpl.getAllStudent();

        model.addAttribute("students",students);
        return "admin_student_list";
    }


    @RequestMapping(value="/mentorAppraises/{mentorId}",method = RequestMethod.GET)
    public String AppraiseList(@PathVariable("mentorId") Integer mentorId,Model model){
        List<Appraise> appraises=adminServiceImpl.getAllAppraiseByMenterId(mentorId);
        model.addAttribute("appraises",appraises);
        return "admin_mentor_appraise_list";
    }

    @RequestMapping(value="/studentAppointment/{studentId}",method = RequestMethod.GET)
    public String StudentAppointmentList(@PathVariable("studentId") Integer studentId,Model model){
        List<Appointment> appointments=adminServiceImpl.getAllAppointmentByStudentId(studentId);
        model.addAttribute("appointments",appointments);
        return "admin_appointment_list";
    }


    @RequestMapping("/plans")
    public String getAllPlan(Model model){
        List<Plan> plans = adminServiceImpl.getAllPlan();

        model.addAttribute("plans",plans);
        return "admin_plan_list";
    }


    @RequestMapping("/feedback/{feedbackId}")
    public String getFeedback(@PathVariable("feedbackId") Integer feedbackId, Model model){
        Feedback feedback = adminServiceImpl.getFeedback(feedbackId);
        adminServiceImpl.checkFeedback(feedbackId);
        model.addAttribute("feedback",feedback);
        return "admin_feedback_detail";
    }

    @RequestMapping(value = "/checkFeedback/",method = RequestMethod.GET)
    @ResponseBody
    public boolean checkFeedback(Integer feedbackId){
        adminServiceImpl.checkFeedback(feedbackId);
        return true;
    }

    @RequestMapping("/plan/{planId}")
    public String getPlan(@PathVariable("planId") Integer planId,Integer feedbackId, Model model){
        Plan plan = adminServiceImpl.getPlan(planId);
        model.addAttribute("plan",plan);
        model.addAttribute("feedbackId",feedbackId);
        return "admin_Plan_detail";
    }

    @RequestMapping("/editPlan")
    public String editPlan(Integer planId,Integer feedbackId,Model model){
        Plan plan=adminServiceImpl.getPlan(planId);
        if(feedbackId==null){
            feedbackId=adminServiceImpl.getPlanLatestFeedback(planId);
        }
        model.addAttribute("feedbackId",feedbackId);
        model.addAttribute("plan",plan);
        return "admin_plan_edit";
    }

    @RequestMapping(value = "/changePlan",method = RequestMethod.POST)
    public String changePlan(Integer planId,Integer feedbackId,String planContent){
        adminServiceImpl.updatePlanContent(planId,planContent);
        adminServiceImpl.changeFeedback(feedbackId);
        return "redirect:/admin/plans";
    }

    @RequestMapping("/mentorAppraisedetails/{appraiseId}")
    public String getPlan(@PathVariable("appraiseId") Integer appraiseId, Model model){
        Appraise appraise = adminServiceImpl.getAppraise(appraiseId);
        model.addAttribute("appraise",appraise);
        return "admin_appraise_detail";
    }

}
