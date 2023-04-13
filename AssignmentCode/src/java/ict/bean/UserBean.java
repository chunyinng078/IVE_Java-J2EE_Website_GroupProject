/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ict.bean;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class UserBean implements Serializable {

    private String userid,username, password, name, role,enable;
    int tel;

    public UserBean(String userid, String username, String password, String name, String role, int tel, String enable) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.name = name;
        this.tel = tel;
        this.role = role;
        this.enable = enable;
    }

    public UserBean() {
        
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getTel() {
        return tel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getEnable() {
        return enable;
    }

    public String toString() {
        return "ID: "+userid+"\nUserName: "+username+"\npassword: "+password+"\nname: "+name+"\nrole "+role+"\ntel "+ tel+"\nenable "+enable;
    }
}
