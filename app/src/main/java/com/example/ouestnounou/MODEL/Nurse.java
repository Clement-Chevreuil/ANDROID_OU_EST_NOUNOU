package com.example.ouestnounou.MODEL;

import java.util.Date;

public class Nurse {

    private int id;
    private String sex;
    private String fist_name;
    private String last_name;
    private String birth;
    private String city;
    private String country;
    private String phone;
    private String adress;
    private String postal_code;
    private String mail;
    private String password;
    private int age_min;
    private int age_max;
    private int nb_children;

    public Nurse(){}

    public Nurse(String fist_name, String last_name, String sex, String birth, String city, String country, String phone, String adress, String postal_code, String mail, String password, int age_min, int age_max, int nb_children) {
        this.sex = sex;
        this.fist_name = fist_name;
        this.last_name = last_name;
        this.birth = birth;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.adress = adress;
        this.postal_code = postal_code;
        this.mail = mail;
        this.password = password;
        this.age_min = age_min;
        this.age_max = age_max;
        this.nb_children = nb_children;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
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

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
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

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNb_children() {
        return nb_children;
    }

    public void setNb_children(int nb_children) {
        this.nb_children = nb_children;
    }
}
