package com.bcahungary.acme;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class WorkItem {

    private String wiid;
    private String description;
    private String type;
    private String status;
    private String date;

    // Konstruktor
    public WorkItem(String wiid, String description, String type, String status, String date) {
        this.wiid = wiid;
        this.description = description;
        this.type = type;
        this.status = status;
        this.date = date;
    }

    // Getterek és Setterek
    public String getWiid() {
        return wiid;
    }

    public void setWiid(String wiid) {
        this.wiid = wiid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WorkItem{"
                + "WIID='" + wiid + '\''
                + ", Description='" + description + '\''
                + ", Type='" + type + '\''
                + ", Status='" + status + '\''
                + ", Date='" + date + '\''
                + '}';
    }
}
