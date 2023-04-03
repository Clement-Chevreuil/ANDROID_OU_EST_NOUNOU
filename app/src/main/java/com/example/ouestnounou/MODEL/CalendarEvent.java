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

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}