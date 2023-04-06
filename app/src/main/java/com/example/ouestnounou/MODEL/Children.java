package com.example.ouestnounou.MODEL;

import java.util.Date;

public class Children {

    private int id;
    private String firstName;
    private String lastName;
    private String birth;
    private String sex;
    private int nurseAccepted;
    private String informations;
    private com.example.ouestnounou.MODEL.Parents parents;
    private Nurse nurse;

    public Children(String firstName, String lastName, String birth, String sex, Parents parents, Nurse nurse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.sex = sex;
        this.nurse = nurse;
        this.parents = parents;
    }
    public Children( String firstName, String lastName, String birth, String sex, Parents parents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.sex = sex;
        this.parents = parents;
    }

    public Children(){}


    //GETTER
    public String getSex() {
        return sex;
    }
    public int getId() {
        return id;
    }
    public String getInformations() {
        return informations;
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
    public com.example.ouestnounou.MODEL.Parents getParents() {
        return parents;
    }
    public Nurse getNurse() {
        return nurse;
    }
    public int getNurseAccepted() {
        return nurseAccepted;
    }

    //SETTER
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setInformations(String informations) {
        this.informations = informations;
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
    public void setParents(com.example.ouestnounou.MODEL.Parents parents) {
        this.parents = parents;
    }
    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
    public void setNurseAccepted(int nurseAccepted) {
        this.nurseAccepted = nurseAccepted;
    }
}
