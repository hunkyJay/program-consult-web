package com.programLearning.ssm.pojo;

public class Student {

    private int id;
    private String name;
    private String studentUsername;
    private String password;
    private String email;
    private String img;

    public Student(){}

    public Student(String studentUsername, String password, String email,String name,String img) {
        this.studentUsername = studentUsername;
        this.password = password;
        this.email = email;
        this.name = name;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



}
