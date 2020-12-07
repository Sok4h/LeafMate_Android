package com.sokah.leafmate;

import java.io.Serializable;

public class LibraryPlant implements Serializable {

    String Description,Name,Sunlight,Type,Watering;

    public  LibraryPlant(){

    }

    public LibraryPlant(String description, String name, String sunlight, String type, String watering) {
        Description = description;
        Name = name;
        Sunlight = sunlight;
        Type = type;
        Watering = watering;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSunlight() {
        return Sunlight;
    }

    public void setSunlight(String sunlight) {
        Sunlight = sunlight;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getWatering() {
        return Watering;
    }

    public void setWatering(String watering) {
        Watering = watering;
    }
}
