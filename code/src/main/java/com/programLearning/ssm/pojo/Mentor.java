package com.programLearning.ssm.pojo;

public class Mentor {
    private int id;
    private String name;
    private String mentorUsername;
    private String password;
    private String introduction;
    private String email;
    private String img;

    public Mentor(){}

    public Mentor(String email,String mentorUsername, String password, String name, String introduction,String img) {
        this.mentorUsername = mentorUsername;
        this.password = password;
        this.name = name;
        this.introduction = introduction;
        this.img = img;
        this.email=email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMentorUsername() {
        return mentorUsername;
    }

    public void setMentorUsername(String mentorUsername) {
        this.mentorUsername = mentorUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
