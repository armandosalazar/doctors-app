package com.example.doctorsapp.models;

public class Date {
    private String names;
    private String lastNames;
    private String phone;
    private String symptoms;
    private boolean urgent;
    private String sex;

    public Date() {
    }

    public Date(String names, String lastNames, String phone, String symptoms, boolean urgent, String sex) {
        this.names = names;
        this.lastNames = lastNames;
        this.phone = phone;
        this.symptoms = symptoms;
        this.urgent = urgent;
        this.sex = sex;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
