package com.timetracker.db.entity;

import java.sql.Time;

/**
 * This is entity for task table.
 */
public class Task {
    private int id;
    private String name;
    private Time time;
    private int userId;
    private int categoryId;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Time getTime() {
        return time;
    }

    public int getUserId() {
        return userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Pattern Builder.
     */
    public static class Builder{
        private Task newTask;

        public Builder() {
            this.newTask = new Task();
        }

        public Builder withId(int id){
            newTask.id = id;
            return this;
        }

        public Builder withName(String name){
            newTask.name = name;
            return this;
        }

        public Builder withTime(Time time){
            newTask.time = time;
            return this;
        }

        public Builder withUserId(int id){
            newTask.userId = id;
            return this;
        }

        public Builder withCategoryId(int id){
            newTask.categoryId = id;
            return this;
        }

        public Builder withStatus(Status status){
            newTask.status = status;
            return this;
        }

        public Task build(){
            return newTask;
        }
    }
}
