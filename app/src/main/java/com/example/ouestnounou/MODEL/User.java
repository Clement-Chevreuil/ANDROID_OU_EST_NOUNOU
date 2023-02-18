package com.example.ouestnounou.MODEL;

public class User {

    public int id;
    private String first_name;
    private String mail;
    public String password;

    public User(int id, String mail, String password) {
        super();
        this.id = id;
        this.mail = mail;
        this.password = password;

    }
    public User(String mail, String password) {
        super();
        this.mail = mail;
        this.password = password;

    }

    public User() {super();}

    public String getMail() {
        return mail;
    }
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setId(int id) {
        this.id = id;
    }


}

