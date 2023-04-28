package com.programLearning.ssm.mapper;

import com.programLearning.ssm.pojo.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MentorMapper {

    Mentor mentorSelect(@Param("mentorUsername") String mentorUsername);
    Student studentSelect(@Param("studentUsername") String studentUsername);
    String mentorLogin(@Param("mentorUsername") String mentorUsername);
    void mentorRegister(@Param("mentorUsername") String mentorUsername, @Param("password") String password,@Param("name") String name, @Param("email") String email);
    void mentorUpdate(@Param("mentorUsername") String mentorUsername,@Param("name")String name,@Param("email")String email,@Param("introduction") String introduction);
    List<Appointment> getAllAppointment(@Param("mentorUsername") String mentorUsername);
    List<Appointment> getAppointmentByStatus(@Param("mentorUsername") String mentorUsername, @Param("status") int status);
    List<Plan> checkPlan(@Param("studentUsername") String studentUsername);
    void appointmentUpdate(@Param("appointmentId") int appointmentId, @Param("status") int status, @Param("updateTime") Date updateTime);
    void addMeetingUrl(@Param("appointmentId") int appointmentId, @Param("meetingUrl") String meetingUrl);
    List<Appraise> checkAppraise(@Param("mentorUsername") String mentorUsername);
    void saveImg(@Param("mentorUsername")String mentorUsername,@Param("img")String img);
}
