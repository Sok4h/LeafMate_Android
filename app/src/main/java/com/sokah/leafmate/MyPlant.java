package com.sokah.leafmate;

import java.io.Serializable;
import java.util.Date;

public class MyPlant implements Serializable {


    private String id,userId,name,userName,type,sunLight,nextWatter;
    private String bornDate, bornTime;


    public MyPlant(String id,String userId, String name, String userName, String type, String sunLight, String bornDate,String nextWatter, String bornTime) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.sunLight=sunLight;
        this.bornDate=bornDate;
        this.userName=userName;

        this.nextWatter = nextWatter;

        this.userId = userId;
        this.bornTime = bornTime;

    }

    public MyPlant(){


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

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
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

    public String getUserId() {
        return userId;
    }

    public String getBornTime() {
        return bornTime;
    }

    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void Water(){


    }

    public String getNextWatter() {
        return nextWatter;
    }

    public void setNextWatter(String nextWatter) {
        this.nextWatter = nextWatter;
    }
}
