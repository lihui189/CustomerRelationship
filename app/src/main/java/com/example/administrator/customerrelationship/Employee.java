package com.example.administrator.customerrelationship;

import android.graphics.Bitmap;

public class Employee {
    private Bitmap imageRealation;
    private Bitmap imageDepart;
    private Bitmap imageEmployee;
    private String relation;
    private String depart;
    private String employee;
    private String Cus_phone;

    public Employee(Bitmap imageRealation, Bitmap imageDepart, Bitmap imageEmployee, String relation, String depart, String employee, String cus_phone) {
        this.imageRealation = imageRealation;
        this.imageDepart = imageDepart;
        this.imageEmployee = imageEmployee;
        this.relation = relation;
        this.depart = depart;
        this.employee = employee;
        Cus_phone = cus_phone;
    }

    public Bitmap getImageRealation() {
        return imageRealation;
    }

    public void setImageRealation(Bitmap imageRealation) {
        this.imageRealation = imageRealation;
    }

    public Bitmap getImageDepart() {
        return imageDepart;
    }

    public void setImageDepart(Bitmap imageDepart) {
        this.imageDepart = imageDepart;
    }

    public Bitmap getImageEmployee() {
        return imageEmployee;
    }

    public void setImageEmployee(Bitmap imageEmployee) {
        this.imageEmployee = imageEmployee;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCus_phone() {
        return Cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        Cus_phone = cus_phone;
    }
}
