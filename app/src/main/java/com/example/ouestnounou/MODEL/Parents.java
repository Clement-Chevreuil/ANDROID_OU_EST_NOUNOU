package com.example.ouestnounou.MODEL;

import java.util.ArrayList;
import java.util.Date;

public class Parents {

    private int id;
    private String firstName;
    private String lastName;
    private String birth;
    private String city;
    private String country;
    private String address;
    private String postalCode;
    private String mail;
    private String password;
    private String phone;
    private String sex;
    private ArrayList<Children> chidrens;

    public Parents(int id, String firstName, String lastName, String birth, String city, String country, String address, String postalCode, String mail, String password, String phone, String sex, ArrayList<Children> childrens) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.city = city;
        this.country = country;
        this.address = address;
        this.postalCode = postalCode;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
        this.chidrens = childrens;
    }
    public Parents(int id, String firstName, String lastName, String birth, String city, String country, String address, String postalCode, String mail, String password, String phone, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.city = city;
        this.country = country;
        this.address = address;
        this.postalCode = postalCode;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
    }

    public Parents( String firstName, String lastName, String birth, String city, String country, String address, String postalCode, String mail, String password, String phone, String sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.city = city;
        this.country = country;
        this.address = address;
        this.postalCode = postalCode;
        this.mail = mail;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
    }

    public Parents(){}

    //GETTER
    public int getId() {
        return id;
    }
    public String getSex() {
        return sex;
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
    public String getMail() {
        return mail;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getPassword() {
        return password;
    }
    public String getPhone() {
        return phone;
    }
    public ArrayList<Children> getChidrens() {
        return chidrens;
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
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}
    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setChidrens(ArrayList<Children> chidrens) {
        this.chidrens = chidrens;
    }
}
