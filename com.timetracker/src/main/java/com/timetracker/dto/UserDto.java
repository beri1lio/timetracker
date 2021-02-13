package com.timetracker.dto;

import java.util.List;

public class UserDto {
    private int id;
    private String name;
    private List<TaskDto> tasks;

    public UserDto() {
    }

    public UserDto(int id, String name, List<TaskDto> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
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

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
