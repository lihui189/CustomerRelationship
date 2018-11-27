package com.example.administrator.customerrelationship;

public class CustSearch {
    private String         relation_id;
    private String         employee_id;
    private String         employee_name;
    private String         employee_depart;
    private String         employee_phone;
    private String         cusunit_id;
    private String         cusunit_name;
    private String         cusunit_area;
    private String         customer_name;
    private String         relation_type;
    private String         cus_job;
    private String         cus_phone;

    public CustSearch(String customer_name,String cusunit_name, String cus_job) {
        this.customer_name = customer_name;
        this.cusunit_name = cusunit_name;
        this.cus_job = cus_job;
    }

    public String getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(String relation_id) {
        this.relation_id = relation_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_depart() {
        return employee_depart;
    }

    public void setEmployee_depart(String employee_depart) {
        this.employee_depart = employee_depart;
    }

    public String getEmployee_phone() {
        return employee_phone;
    }

    public void setEmployee_phone(String employee_phone) {
        this.employee_phone = employee_phone;
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

    public String getCusunit_area() {
        return cusunit_area;
    }

    public void setCusunit_area(String cusunit_area) {
        this.cusunit_area = cusunit_area;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getRelation_type() {
        return relation_type;
    }

    public void setRelation_type(String relation_type) {
        this.relation_type = relation_type;
    }

    public String getCus_job() {
        return cus_job;
    }

    public void setCus_job(String cus_job) {
        this.cus_job = cus_job;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }
}
