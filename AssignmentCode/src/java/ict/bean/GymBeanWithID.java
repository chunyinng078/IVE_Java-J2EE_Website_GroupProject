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
public class GymBeanWithID implements Serializable {

    private String id;
    private String centerName;
    private String states;
    private String hrRate;
    private String description;
    private int imageID;

    public GymBeanWithID() {

    }

    public GymBeanWithID(String id, String centerName, String description, String states, String hrRate, int imageID) {
       this.id = id;
        this.description = description;
        this.hrRate = hrRate;
        this.imageID = imageID;
        this.centerName = centerName;
        this.states = states;
    }

    public void setCenterID(String id) {
        this.id = id;
    }

    public String getCenterID() {
        return id;
    }
    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getStates() {
        return states;
    }

    public void setHrRate(String hrRate) {
        this.hrRate = hrRate;
    }

    public String getHrRate() {
        return hrRate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }
}
