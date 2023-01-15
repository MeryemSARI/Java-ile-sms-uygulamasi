package com.example.sms.models;

public class MesajModel {
    String name;
    String description;

    public MesajModel() {
    }

    public MesajModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
