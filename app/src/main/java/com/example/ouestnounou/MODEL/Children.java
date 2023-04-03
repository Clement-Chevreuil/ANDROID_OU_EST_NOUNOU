package com.example.ouestnounou.MODEL;

import java.util.Date;

public class Children {

    private int id;
    private String fist_name;
    private String last_name;
    private String birth;

    private String sex;

    private int nurse_accepted;

    private String informations;

    private com.example.ouestnounou.MODEL.Parents parents;
    private Nurse nurse;

    public Children( String fist_name, String last_name, String birth, String sex, Parents parents, Nurse nurse) {
        this.fist_name = fist_name;
        this.last_name = last_name;
        this.birth = birth;
        this.sex = sex;
        this.nurse = nurse;
        this.parents = parents;
    }
    public Children( String fist_name, String last_name, String birth, String sex, Parents parents) {
        this.fist_name = fist_name;
        this.last_name = last_name;
        this.birth = birth;
        this.sex = sex;
        this.parents = parents;
    }

    public Children(){}
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInformations() {
        return informations;
    }

    public void setInformations(String informations) {
        this.informations = informations;
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

    public com.example.ouestnounou.MODEL.Parents getParents() {
        return parents;
    }

    public void setParents(com.example.ouestnounou.MODEL.Parents parents) {
        this.parents = parents;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public int getNurse_accepted() {
        return nurse_accepted;
    }

    public void setNurse_accepted(int nurse_accepted) {
        this.nurse_accepted = nurse_accepted;
    }
}
