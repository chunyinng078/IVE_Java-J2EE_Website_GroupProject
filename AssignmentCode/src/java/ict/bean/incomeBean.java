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
public class incomeBean implements Serializable{

    private String id, name, income;

    public incomeBean(String id, String name, String income) {
        this.id = id;
        this.name = name;
        this.income = income;

    }

    public incomeBean() {

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

    public void setIncome(String income) {
        this.income = income;
    }

    public String getIncome() {
        return income;
    }

}
