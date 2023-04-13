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
public class bookingBean implements Serializable{

    private String id,  date,  type,  typeID,  custID, time, status;

    public bookingBean(String id, String date, String type, String typeID, String custID,String time,String status) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.typeID = typeID;
        this.custID = custID;
        this.time = time;
        this.status = status;
    }

    public bookingBean() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setTypeId(String typeID) {
        this.typeID = typeID;
    }

    public String getTypeId() {
        return typeID;
    }

    public void setCustid(String custID) {
        this.custID = custID;
    }

    public String getCustid() {
        return custID;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
