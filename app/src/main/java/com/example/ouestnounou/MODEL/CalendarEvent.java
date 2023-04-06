package com.example.ouestnounou.MODEL;

public class CalendarEvent {

    private int id;
    private String date;
    private String startTime;
    private String endTime;
    private boolean accepted;
    private Children children;

    public CalendarEvent(String date, String startTime, String endTime, boolean accepted, Children children) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accepted = accepted;
        this.children = children;
    }

    public CalendarEvent(String date, String startTime, String endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public CalendarEvent() {

    }

    //GETTER
    public Children getChildren() {
        return children;
    }
    public int getId() {
        return id;
    }
    public String getDate() {
        return date;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public boolean isAccepted() {
        return accepted;
    }

    //SETTER
    public void setChildren(Children children) {
        this.children = children;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}