package com.example.ouestnounou.MODEL;

import java.util.Date;

public class Parents {

    private int id;
    private String fist_name;
    private String last_name;
    private Date birth;
    private String city;
    private String country;
    private String adress;
    private String postal_code;
    private String mail;
    private String password;
    private String phone;

    private String sex;

    public Parents(int id, String fist_name, String last_name, Date birth, String city, String country, String adress, String postal_code, String mail, String password, String phone, String sex) {
        this.id = id;
        this.fist_name = fist_name;
        this.last_name = last_name;
        this.birth = birth;
        this.city = city;
        this.country = country;
        this.adress = adress;
        this.postal_code = postal_code;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
    }

    public Parents(){}

    public int getId() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFist_name() {
        return fist_name;
    }

    public void setFist_name(String fist_name) {
        this.fist_name = fist_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
