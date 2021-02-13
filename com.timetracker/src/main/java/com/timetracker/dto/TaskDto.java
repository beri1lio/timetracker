package com.timetracker.dto;

import java.sql.Time;

public class TaskDto {
    private int id;
    private String name;
    private Time time;
    private String userName;

    public TaskDto() {
    }

    public TaskDto(int id, String name, Time time, String userName) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.userName = userName;
    }

    public TaskDto(int id, String name, Time time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
