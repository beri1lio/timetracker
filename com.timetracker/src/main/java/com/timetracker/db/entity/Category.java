package com.timetracker.db.entity;

/**
 * This is entity for category table.
 */
public class Category {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Pattern Builder.
     */
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
