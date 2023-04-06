package com.example.ouestnounou.MODEL;

import java.util.Date;

public class School {

    private int id;
    public String phone;
    private String name;
    private String city;
    private String country;
    private String address;
    private String postalCode;
    private String mail;

    public School( String name, String phone, String address, String city, String postalCode, String mail, String country) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.mail = mail;
        this.country = country;
    }

    public School(){}

    //GETTER
    public int getId() {
        return id;
    }
    public String getPhone() {
        return phone;
    }
    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getAddress() {
        return address;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getMail() {
        return mail;
    }

    //SETTER
    public void setId(int id) {
        this.id = id;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
