/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.entity;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author acer
 */
public class User {
    private int id;
    private String name;
    private String surName;
    private String phone;
    private String email;
    private String password;
    private String profileDesc;
    private String address;
    private Date  brithDate;
    private Country nationality;
    private Country birthPlace;
    private List<UserSkill> skills;
    

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String surName, String phone, String email,String profileDesc,String address, Date brithDate, Country nationality, Country birthPlace) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.email = email;
        this.brithDate = brithDate;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
        this.profileDesc=profileDesc;
        this.address=address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(Date brithDate) {
        this.brithDate = brithDate;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<UserSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkill> skills) {
        this.skills = skills;
    }

    public String getProfileDesc() {
        return profileDesc;
    }

    public void setProfileDesc(String profileDesc) {
        this.profileDesc = profileDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surName=" + surName + ", phone=" + phone + ", email=" + email + ", profileDesc=" + profileDesc + ", address=" + address + ", brithDate=" + brithDate + ", nationality=" + nationality + ", birthPlace=" + birthPlace + ", skills=" + skills + '}';
    }
    
   
    
}
