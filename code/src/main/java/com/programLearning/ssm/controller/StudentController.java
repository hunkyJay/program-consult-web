package com.programLearning.ssm.controller;

import com.programLearning.ssm.pojo.*;
import com.programLearning.ssm.service.AdminService;
import com.programLearning.ssm.service.StudentService;
import com.programLearning.ssm.service.util.RandonCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.programLearning.ssm.service.util.RandonCodeGenerator.RANDOMCODEKEY;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentServiceImpl;

    @RequestMapping("/register")
    public String register(){return "student_register";}

    @RequestMapping("/doRegister")
    public String doRegister(String studentUsername,String password, String name,String email,Model model){
        boolean result = studentServiceImpl.studentRegister(studentUsername,password,name,email);
        if (result){
            return "redirect:/student/login";
        }else{
            model.addAttribute("msg","The Username has been used, please try another username");
            return "student_register";
        }
    }

    @RequestMapping("/doLogin")
    public String doLogin(String studentUsername, String password, String verifyCode, HttpSession session, Model model){
        boolean result = studentServiceImpl.studentLogin(studentUsername,password);
        if (!result){
            System.out.println("password is wrong");
        }
        boolean verify = verifyCode.equalsIgnoreCase((String)session.getAttribute(RANDOMCODEKEY));
        if (!verify){
            System.out.println("verify code is wrong");
        }
        if(result&&verify){
            session.setAttribute("student_username",studentUsername );
            return "redirect:/student/";
        }else{
            model.addAttribute("msg","the username/password/verifyCode is wrong,please try again");
            return "student_login";
        }
    }

    @RequestMapping("/login")
    public String login(){
        return "student_login";
    }

    @RequestMapping("/doLogout")
    public String logout( HttpSession session){
        session.removeAttribute("student_username");
        return "redirect:/student/login";
    }

    @RequestMapping("/verify")
    public void verify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandonCodeGenerator randomValidateCode = new RandonCodeGenerator();
        try {
            randomValidateCode.getRandcode(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updateProfile")
    public String updateInfo(HttpSession session,Model model){
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_update_profile";
    }

    @RequestMapping("/doUpdateProfile")
    public String doUpdateInfo(String studentUsername,String name,String email){
        studentServiceImpl.updateStuInfo(studentUsername,name,email);
        return "redirect:/student/updateProfile";
    }

    @RequestMapping("/checkPlan")
    public String checkPlan(Model model,HttpSession session){

        String studentUsername = (String) session.getAttribute("student_username");
        List<Plan> plans = studentServiceImpl.checkPlan(studentUsername);
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        model.addAttribute("plans",plans);
        return "student_plans";
    }

    @RequestMapping(value ="/checkPlan/{planId}", method = RequestMethod.GET)
    public String getPlan(@PathVariable("planId") int planId,Model model,HttpSession session){
        Plan plan = studentServiceImpl.getPlan(planId);
        List<Plan> plans = new ArrayList<>();
        plans.add(plan);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        model.addAttribute("plans",plan);
        return "student_plans";
    }

    @RequestMapping(value="/givePlanFeedback/{planId}", method = RequestMethod.GET)
    public String givePlanFeedback(@PathVariable("planId") int planId,Model model,HttpSession session){
        Plan plan = studentServiceImpl.getPlan(planId);
        model.addAttribute("plan",plan);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_give_plan_feedback";
    }

    @RequestMapping("/doGivePlanFeedback")
    public String doGivePlanFeedBack(int planId,String content,HttpSession session,Model model){
        String feedbackUsername = (String) session.getAttribute("student_username");
        studentServiceImpl.givePlanFeedback(feedbackUsername,planId,content);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "redirect:/student/feedbacks";
    }

    @RequestMapping("/feedbacks")
    public String checkFeedback(HttpSession session,Model model){
        String feedbackUsername = (String) session.getAttribute("student_username");
        System.out.println(feedbackUsername);
        List<Feedback> feedbacks = studentServiceImpl.getAllFeedback(feedbackUsername);
        model.addAttribute("feedbacks",feedbacks);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_feedback";
    }

    @RequestMapping("/checkMentor")
    public String checkMentor(Model model,HttpSession session){
        List<Mentor> mentors = studentServiceImpl.checkMentor();
        model.addAttribute("mentors",mentors);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_mentor";
    }

    @RequestMapping("/checkMentorByKeyword")
    public String checkMentorByKeyword(String keyword,Model model,HttpSession session){
        List<Mentor> mentors = studentServiceImpl.checkMentorByKeyword(keyword);
        model.addAttribute("mentors",mentors);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_mentor";
    }

    @RequestMapping(value ="/checkMentorAppraise/{mentorUsername}", method = RequestMethod.GET)
    public String checkMentorAppraise(@PathVariable("mentorUsername") String mentorUsername,Model model,HttpSession session){
        List<Appraise> appraises = studentServiceImpl.checkMentorAppraise(mentorUsername);
        Mentor mentor = studentServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        model.addAttribute("appraises",appraises);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_check_appraise";
    }

    @RequestMapping(value="/giveAppraise/{appointmentId}",method = RequestMethod.GET)
    public String giveAppraise(@PathVariable("appointmentId") int appointmentId,Model model,HttpSession session){
        Appointment appointment = studentServiceImpl.getAppointment(appointmentId);
        Mentor mentor = studentServiceImpl.getMentor(appointment.getMentorUsername());
        model.addAttribute("appointment",appointment);
        model.addAttribute("mentor",mentor);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_give_appraise";
    }

    @RequestMapping("/doGiveAppraise")
    public String doGiveAppraise(int appointmentId,String mentorName,String mentorUsername,String appraise,Model model){
        boolean result = studentServiceImpl.giveAppraise(appointmentId,mentorUsername,appraise);
        if (result){
            return "redirect:/student/appointments";
        }else{
            model.addAttribute("msg","Give Appraise Failed ");
        }
        return "student_give_appraise";
    }

    @RequestMapping("/appointments")
    public String getAllAppointment(Model model,HttpSession session){
        String studentUsername = (String) session.getAttribute("student_username");
        List<Appointment> appointments = studentServiceImpl.getAllAppointment(studentUsername);
        List<AppointmentTemp> appointmentTemps = new ArrayList<>();
        for(int i =0 ; i < appointments.size();i++){
            Appointment appointment = appointments.get(i);
            Mentor mentor = studentServiceImpl.getMentor(appointment.getMentorUsername());
            String studentName = studentServiceImpl.getStudent(studentUsername).getName();
            AppointmentTemp appointmentTemp = new AppointmentTemp(appointment,mentor.getName(),studentName);
            appointmentTemps.add(appointmentTemp);
        }
        model.addAttribute("appointments",appointmentTemps);
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_appointment";
    }

    @RequestMapping(value ="/appointment/{mentorUsername}",method = RequestMethod.GET)
    public String makeAppointment(@PathVariable("mentorUsername") String mentorUsername,Model model,HttpSession session){
        Mentor mentor = studentServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_make_appointment";
    }

    @RequestMapping("/doAppointment")
    public String doMakeAppointment(String mentorUsername, String appointmentTime, Model model, HttpSession session){
        String studentUsername = (String) session.getAttribute("student_username");
        System.out.println(mentorUsername);
        boolean result = studentServiceImpl.makeAppointment(studentUsername,mentorUsername,appointmentTime);
        if (result){
            return "redirect:/student/appointments";
        }
        Mentor mentor = studentServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        model.addAttribute("msg","Make Appointment Failed ");
        return "student_make_appointment";
    }


    @RequestMapping(value="/cancelAppointment/",method = RequestMethod.GET)
    @ResponseBody
    public Boolean cancelAppointment(int appointmentId,HttpSession session, Model model) throws InterruptedException {
        studentServiceImpl.cancelAppointment(appointmentId);
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return true;
    }



    @RequestMapping("/")
    public String index(HttpSession session,Model model){
        String studentUsername = (String) session.getAttribute("student_username");
        List<Appointment> appointments = studentServiceImpl.getAllAppointment(studentUsername);
        int numOfWaitBooking = 0;
        int numOfSuccessBooking = 0;
        int numOfPlan = 0;
        int numOfValidFeedback = 0;
        for(int i = 0; i < appointments.size();i++){
            if (appointments.get(i).getStatus() == 0){
                numOfWaitBooking++;
            }else if(appointments.get(i).getStatus() == 1){
                numOfSuccessBooking++;
            }
        }

        List<Plan> plans = studentServiceImpl.checkPlan(studentUsername);
        numOfPlan = plans.size();

        List<Feedback> feedbacks = studentServiceImpl.getAllFeedback(studentUsername);
        for(int i = 0; i < feedbacks.size();i++){
            if (feedbacks.get(i).getIsCheck()==2){
                numOfValidFeedback++;
            }
        }



        model.addAttribute("numOfSuccessBooking",numOfSuccessBooking);
        model.addAttribute("numOfPlan",numOfPlan);
        model.addAttribute("numOfWaitBooking",numOfWaitBooking);
        model.addAttribute("numOfValidFeedback",numOfValidFeedback);
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        System.out.println(student.getImg());
        return "student_homepage";
    }

    @RequestMapping("/acquirePlan")
    public String acquirePlan(HttpSession session, Model model){
        String studentUsername = (String) session.getAttribute("student_username");
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "student_acquire_plan";
    }

    @RequestMapping("/doAcquireJavaPlan")
    public String acquireJavaPlan(String JavaSE,String JavaWeb,String MySQL,String SpringMVC,String MyBatis,String SpringBoot,String Linux,String JVM,String SpringCloud,String JavaMiddleware,HttpSession session,Model model){
        String studentUsername = (String) session.getAttribute("student_username");
        studentServiceImpl.acquireJavaPlanPlan(studentUsername,JavaSE,JavaWeb,MySQL,SpringMVC,MyBatis,SpringBoot,Linux,JVM,SpringCloud,JavaMiddleware);
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "redirect:/student/checkPlan";
    }

    @RequestMapping("/doAcquirePythonPlan")
    public String acquirePythonPlan(String PythonBasics,String PythonLinux,String Database,String PythonWebFrontEnd,String PythonWebBackEnd,String DataAnalytics,String ArtificialIntelligence,HttpSession session,Model model){
        String studentUsername = (String) session.getAttribute("student_username");
        studentServiceImpl.acquirePythonPlan(studentUsername,PythonBasics,PythonLinux,Database,PythonWebFrontEnd,PythonWebBackEnd,DataAnalytics,ArtificialIntelligence);
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "redirect:/student/checkPlan";
    }

    @RequestMapping("/doAcquireCPlan")
    public String acquireCPlan(String CBasic,String CProficiency,String CPlusBasic,String CPlusProficiency,String Windows,String CLinux,HttpSession session,Model model){
        String studentUsername = (String) session.getAttribute("student_username");
        studentServiceImpl.acquireCPlan(studentUsername,CBasic,CProficiency,CPlusBasic,CPlusProficiency,Windows,CLinux);
        Student student = studentServiceImpl.getStudent(studentUsername);
        model.addAttribute("student",student);
        return "redirect:/student/checkPlan";
    }

    @RequestMapping("/uploadPortrait")
    @ResponseBody
    public Boolean uploadPortrait(String picUrl,HttpSession session) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dateStr = sdf.format(date);
        String envPath = System.getenv("program_learning_path");
        //String filename1 = "/Users/tandong/desktop/5620Stage2/src/main/webapp/static/picture/" + dateStr+ ".jpg";  //下载路径及下载图片名称
        String filename1 = envPath + "5620Stage2/src/main/webapp/static/picture/" + dateStr+ ".jpg";  //下载路径及下载图片名称
        String img =  dateStr+ ".jpg";
        String studentUsername = (String) session.getAttribute("student_username");
        studentServiceImpl.saveImg(studentUsername,img);
        ServletContext servletContext = session.getServletContext();
        String location = servletContext.getRealPath("static");
        String filename2 = location+"/picture/"+dateStr+ ".jpg";
        safeFile(picUrl,filename1);
        safeFile(picUrl,filename2);

        return true;
    }

    public void safeFile(String picUrl,String filename) throws IOException {
        // 构造URL

        URL url = new URL(picUrl);

        // 打开连接

        URLConnection con = url.openConnection();

        // 输入流

        InputStream is = con.getInputStream();

        // 1K的数据缓冲

        byte[] bs = new byte[1024];


        // 读取到的数据长度

        int len;

        // 输出的文件流
        //String abc = AdminController.class.getClassLoader().getResource("/").getPath()+"static/";

        File file = new File(filename);

        FileOutputStream os = new FileOutputStream(file, true);

        // 开始读取

        while ((len = is.read(bs)) != -1) {

            os.write(bs, 0, len);

        }




        // 完毕，关闭所有链接

        os.close();

        is.close();


    }
}
