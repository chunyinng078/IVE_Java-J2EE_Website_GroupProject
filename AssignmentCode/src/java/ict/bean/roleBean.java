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
public class roleBean implements Serializable{

    private String id, name, description, type, enable;

    public roleBean(String id, String name, String description, String type, String enable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.name = name;
        this.type = type;
        this.enable = enable;
    }

    public roleBean() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getEnable() {
        return enable;
    }

    public String toString() {
        return "id: " + id + "\nName: " + name + "\nDescription: " + description + "\nType: " + type + "\nrole ";
    }
}
