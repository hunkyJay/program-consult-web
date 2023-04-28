package com.programLearning.ssm.controller;


import com.programLearning.ssm.pojo.*;
import com.programLearning.ssm.service.MentorService;
import com.programLearning.ssm.service.util.RandonCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.programLearning.ssm.service.util.RandonCodeGenerator.RANDOMCODEKEY;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private MentorService mentorServiceImpl;

    @RequestMapping("/register")
    public String register(){return "mentor_register";}

    @RequestMapping("/doRegister")
    public String doRegister(String mentorUsername, String password, String name, String email, Model model){
        boolean result = mentorServiceImpl.mentorRegister(mentorUsername,password,name,email);
        if (result){
            return "redirect:/mentor/login";
        }else{
            model.addAttribute("msg","The Username has been used, please try another username");
            return "mentor_register";
        }
    }

    @RequestMapping("/doLogin")
    public String doLogin(String mentorUsername, String password, String verifyCode, HttpSession session, Model model){
        boolean result = mentorServiceImpl.mentorLogin(mentorUsername,password);
        if(!result){
            model.addAttribute("msg","Wrong username or password, please try again");
            return "mentor_login";
        }
        boolean verify = verifyCode.equalsIgnoreCase((String)session.getAttribute(RANDOMCODEKEY));
        if(!verify){
            model.addAttribute("msg","Wrong verify code, please try again");
            return "mentor_login";
        }

        session.setAttribute("mentor_username",mentorUsername );
        return "redirect:/mentor/";

    }

    @RequestMapping("/login")
    public String login(){
        return "mentor_login";
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

    @RequestMapping("/updateInfo")
    public String updateInfo(Model model, HttpSession session){
        String mentorUsername = (String) session.getAttribute("mentor_username");
        Mentor mentor = mentorServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        return "mentor_profile";
    }

    @RequestMapping("/doUpdateInfo")
    public String doUpdateInfo(String mentorUsername, String name, String email, String introduction, Model model){
        Mentor mentor = mentorServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        boolean result = mentorServiceImpl.updateMentorInfo(mentorUsername,name,email, introduction);
        return "redirect:/mentor/updateInfo";
    }

    @RequestMapping("/appointment")
    public String checkReceivedAppointment(Model model,HttpSession session){
        String mentorUsername = (String) session.getAttribute("mentor_username");
        Mentor mentor = mentorServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        List<Appointment> appointments = mentorServiceImpl.checkReceivedAppointment(mentorUsername);
        List<AppointmentTemp> appointmentTemps = new ArrayList<>();
        for(int i =0 ; i < appointments.size();i++){
            Appointment appointment = appointments.get(i);
            Student student = mentorServiceImpl.getStudent(appointment.getStudentUsername());
            String mentorName = mentorServiceImpl.getMentor(mentorUsername).getName();
            AppointmentTemp appointmentTemp = new AppointmentTemp(appointment,mentorName,student.getName());
            appointmentTemps.add(appointmentTemp);
        }
        model.addAttribute("appointments",appointmentTemps);
        return "mentor_appointment";
    }

    @RequestMapping("/accepted_appointment")
    public String checkAcceptedAppointment(Model model,HttpSession session){
        String mentorUsername = (String) session.getAttribute("mentor_username");
        Mentor mentor = mentorServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        List<Appointment> appointments = mentorServiceImpl.checkAcceptedAppointment(mentorUsername);
        List<AppointmentTemp> appointmentTemps = new ArrayList<>();
        for(int i =0 ; i < appointments.size();i++){
            Appointment appointment = appointments.get(i);
            Student student = mentorServiceImpl.getStudent(appointment.getStudentUsername());
            String mentorName = mentorServiceImpl.getMentor(mentorUsername).getName();
            AppointmentTemp appointmentTemp = new AppointmentTemp(appointment,mentorName,student.getName());
            appointmentTemps.add(appointmentTemp);
        }
        model.addAttribute("appointments",appointmentTemps);
        return "mentor_accepted_appointment";
    }

    @RequestMapping("/confirm_appointment")
    @ResponseBody
    public Boolean confirmAppointment(int appointmentId){
        return mentorServiceImpl.confirmAppointment(appointmentId);
    }

    @RequestMapping("/refuse_appointment")
    @ResponseBody
    public Boolean refuseAppointment(int appointmentId){
        return mentorServiceImpl.refuseAppointment(appointmentId);
    }

    @RequestMapping("/complete_appointment")
    @ResponseBody
    public Boolean completeAppointment(int appointmentId){
        return mentorServiceImpl.completeAppointment(appointmentId);
    }

    @RequestMapping("/add_meetingurl")
    @ResponseBody
    public Boolean addMeetingUrl(int appointmentId, String url){
        return mentorServiceImpl.addMeetingUrl(appointmentId, url);
    }

    @RequestMapping("/Student_Plan")
    public String checkStudentPlan(String studentUsername, Model model, HttpSession session){
        List<Plan> plans = mentorServiceImpl.checkStudentPlan(studentUsername);
        model.addAttribute("plans",plans);
        String mentorUsername = (String) session.getAttribute("mentor_username");
        Mentor mentor = mentorServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);

        return "mentor_checkplan";
    }

    @RequestMapping("/appraise")
    public String checkAppraise(Model model,HttpSession session){
        String mentorUsername = (String) session.getAttribute("mentor_username");
        List<Appraise> appraiseList = mentorServiceImpl.checkAppraise(mentorUsername);
        model.addAttribute("appraises",appraiseList);
        Mentor mentor = mentorServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        return "mentor_appraise";
    }

    @RequestMapping("/")
    public String index(Model model, HttpSession session){
        String mentorUsername = (String) session.getAttribute("mentor_username");
        Mentor mentor = mentorServiceImpl.getMentor(mentorUsername);
        model.addAttribute("mentor",mentor);
        List<Appraise> appraiseList = mentorServiceImpl.checkAppraise(mentorUsername);
        List<Appointment> appointments = mentorServiceImpl.checkReceivedAppointment(mentorUsername);
        List<Appointment> acceptedAppointments = mentorServiceImpl.checkAcceptedAppointment(mentorUsername);
        int numOfAppraises = appraiseList.size();
        int numOfAppointments = appointments.size();
        int numOfAcceptedAppointments = acceptedAppointments.size();
        model.addAttribute("numOfAppointments",numOfAppointments);
        model.addAttribute("numOfAcceptedAppointments",numOfAcceptedAppointments);
        model.addAttribute("numOfAppraises",numOfAppraises);
        return "mentor_homepage";
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
        String mentortUsername = (String) session.getAttribute("mentor_username");
        mentorServiceImpl.saveImg(mentortUsername,img);
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
