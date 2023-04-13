package ict.bean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;

/**
 *
 * @author a1
 */
public class TrainerBean implements Serializable {

    private int id;
    private String name;
    private String states;
    private int hrRate;
    private String description;
    private String sportType;
    private int imageID;

    public TrainerBean() {

    }

    public TrainerBean(int id, String name, String states, int hrRate, String description,String sportType, int imageID) {
        this.description = description;
        this.hrRate = hrRate;
        this.id = id;
        this.imageID = imageID;
        this.name = name;
        this.states = states;
        this.sportType = sportType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setStates(String states) {
        this.states = states;
    }

    public String getStates() {
        return states;
    }
    
    public void setHrRate(int hrRate) {
        this.hrRate = hrRate;
    }

    public int getHrRate() {
        return hrRate;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public String getSportType() {
        return sportType;
    }
    
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }
}
