package com.example.sms.models;

public class RehberModel {
    String name;
    String numara;
    String img_url;

    public RehberModel() {
    }

    public RehberModel(String name, String numara, String img_url) {
        this.name = name;
        this.numara = numara;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
