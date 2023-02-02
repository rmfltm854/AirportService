package com.example.TestVer.model.DTO;

import lombok.Setter;

public class UserVO {
    private String id;
    private String pw;
    private String name;
    private int age;
    private String Tel;
    private String Email;
    private String Role;

//    public UserVO(String id, String pw, String name, int age, String tel, String email, String role) {
//        this.id = id;
//        this.pw = pw;
//        this.name = name;
//        this.age = age;
//        Tel = tel;
//        Email = email;
//        Role = role;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getRole() {
        return Role;
    }
}
