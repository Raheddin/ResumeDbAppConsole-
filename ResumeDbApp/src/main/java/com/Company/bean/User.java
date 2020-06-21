/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Company.bean;

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

    public User() {
    }

    public User(int id, String name, String surName, String phone, String email) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.phone = phone;
        this.email = email;
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", surName=" + surName + ", phone=" + phone + ", email=" + email + '}';
    }
    
    
    
}
