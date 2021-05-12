package com.bilkent.covidmonitoringservice.request;

import com.bilkent.covidmonitoringservice.entity.Gender;
import com.bilkent.covidmonitoringservice.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class UserRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Positive
    private Integer age;

    @NotNull
    private Gender gender;


    public User toUser(){
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setAge(this.age);
        user.setGender(this.gender);
        return user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
