package com.driver.model;

import lombok.Builder;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name = "admin")
@Builder

public  class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Integer adminId;

private String username;

private String password;


    public Admin(Integer adminId, String username, String password) {
        this.adminId = adminId;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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
}