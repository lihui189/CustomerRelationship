package com.example.administrator.customerrelationship;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Contact implements Serializable {
    private Bitmap photoId;
    private String name;
    private String phone;
    private String job;

    public Contact( Bitmap photoId, String name, String phone,String job) {
        super();
        this.photoId = photoId;
        this.name = name;
        this.phone = phone;
        this.job = job;
    }

    public Bitmap getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Bitmap photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
