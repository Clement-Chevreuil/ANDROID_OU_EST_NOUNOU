package com.example.ouestnounou.MODEL;

import java.util.Date;

public class Nurse {

    private int id;
    private String sex;
    private String firstName;
    private String lastName;
    private String birth;
    private String city;
    private String country;
    private String phone;
    private String address;
    private String postalCode;
    private String mail;
    private String password;
    private int ageMin;
    private int ageMax;
    private int nbChildren;

    public Nurse(){}

    public Nurse(String firstName, String lastName, String sex, String birth, String city, String country, String phone, String address, String postalCode, String mail, String password, int ageMin, int ageMax, int nbChildren) {
        this.sex = sex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.mail = mail;
        this.password = password;
        this.ageMin = ageMin;
        this.ageMax = ageMax;
        this.nbChildren = nbChildren;
    }


    public String getSex() {
        return sex;
    }
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getBirth() {
        return birth;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getaddress() {
        return address;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getMail() {
        return mail;
    }
    public String getPassword() {
        return password;
    }
    public int getAgeMax() {
        return ageMax;
    }
    public String getPhone() {
        return phone;
    }
    public int getNbChildren() {
        return nbChildren;
    }
    public int getAgeMin() {
        return ageMin;
    }


    //SETTER
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    
    public void setaddress(String address) {
        this.address = address;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }
    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setNbChildren(int nbChildren) {
        this.nbChildren = nbChildren;
    }
}
