package com.timetracker.db.entity;

public class Category {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class Builder{
        private Category category;

        public Builder() {
            this.category = new Category();
        }

        public Builder withId(int id){
            category.id = id;
            return this;
        }

        public Builder withName(String name){
            category.name = name;
            return this;
        }

        public Category build(){
            return category;
        }
    }
}
