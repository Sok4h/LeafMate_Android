package com.sokah.leafmate;

import java.io.Serializable;
import java.util.Date;

public class Plant implements Serializable {

    private String id,name,userName,type,sunLight;
    private Date bornDate;


    public Plant(String id, String name,String userName, String type,String sunLight,Date bornDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sunLight=sunLight;
        this.bornDate=bornDate;
        this.userName=userName;
    }
    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSunLight() {
        return sunLight;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public void setSunLight(String sunLight) {
        this.sunLight = sunLight;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void Water(){


    }
}
