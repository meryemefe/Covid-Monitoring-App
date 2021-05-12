package com.bilkent.covidmonitoringservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String email;

    private Integer age;

    private Gender gender;

    public User mergeUser(User userToMerge) {
        userToMerge.setUsername(Optional.ofNullable(userToMerge.getUsername()).orElse(this.username));
        userToMerge.setPassword(Optional.ofNullable(userToMerge.getPassword()).orElse(this.password));
        userToMerge.setEmail(Optional.ofNullable(userToMerge.getEmail()).orElse(this.email));
        userToMerge.setAge(Optional.ofNullable(userToMerge.getAge()).orElse(this.age));
        userToMerge.setGender(Optional.ofNullable(userToMerge.getGender()).orElse(this.gender));
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}