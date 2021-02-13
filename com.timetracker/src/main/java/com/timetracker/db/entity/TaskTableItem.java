package com.timetracker.db.entity;

import java.sql.Time;

public class TaskTableItem {
    private int id;
    private String name;
    private String categoryName;
    private Time time;
    private String userName;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Time getTime() {
        return time;
    }

    public String getUserName() {
        return userName;
    }

    public static class Builder{
        private TaskTableItem item;

        public Builder() {
            this.item = new TaskTableItem();
        }

        public Builder withId(int id){
            item.id = id;
            return this;
        }

        public Builder withName(String name){
            item.name = name;
            return this;
        }

        public Builder withCategoryString(String name){
            item.categoryName = name;
            return this;
        }

        public Builder withTime(Time time){
            item.time = time;
            return this;
        }

        public Builder withUserString(String name){
            item.userName = name;
            return this;
        }

        public TaskTableItem build(){
            return item;
        }

    }
}
