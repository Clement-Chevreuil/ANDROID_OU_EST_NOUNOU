package com.example.ouestnounou.MODEL;

import java.util.Date;

public class School {

    private int id;
    public Integer phone;
    private String name;
    private String city;
    private String country;
    private String address;
    private String postal_code;
    private String mail;

    public School( String name, String phone, String address, String city, String postal_code) {
        this.name = name;
        this.phone = Integer.parseInt(phone);
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.mail = "temp";
        this.country = "temp";
    }

    public School(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
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
}
