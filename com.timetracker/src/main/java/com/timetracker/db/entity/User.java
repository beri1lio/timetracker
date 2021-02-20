package com.timetracker.db.entity;

/**
 * This is entity for user table.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private String password;
    private Role role;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Pattern Builder.
     */
    public static class Builder{
        private User user;

        public Builder() {
            this.user = new User();
        }

        public Builder withId(int id){
            user.id = id;
            return this;
        }

        public Builder withName(String name){
            user.name = name;
            return this;
        }

        public Builder withLogin(String login){
            user.login = login;
            return this;
        }

        public Builder withPassword(String password){
            user.password = password;
            return this;
        }

        public Builder withRole(Role role){
            user.role = role;
            return this;
        }

        public User build(){
            return user;
        }
    }
}
