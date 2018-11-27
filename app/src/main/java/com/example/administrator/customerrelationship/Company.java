package com.example.administrator.customerrelationship;

/**
 * Created by Administrator on 2018\7\10 0010.
 */

public class Company {
    String cusunit_id;
    String cusunit_name;
    String sortLetters;
    String cusunit_area;

    public Company(String cusunit_name, String cusunit_area) {
        this.cusunit_name = cusunit_name;
        this.cusunit_area = cusunit_area;
    }

    public String getCusunit_id() {
        return cusunit_id;
    }

    public void setCusunit_id(String cusunit_id) {
        this.cusunit_id = cusunit_id;
    }

    public String getCusunit_name() {
        return cusunit_name;
    }

    public void setCusunit_name(String cusunit_name) {
        this.cusunit_name = cusunit_name;
    }

    public String getSortLetters() {
        return sortLetters;
    }

    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }

    public String getCusunit_area() {
        return cusunit_area;
    }

    public void setCusunit_area(String cusunit_area) {
        this.cusunit_area = cusunit_area;
    }
}
