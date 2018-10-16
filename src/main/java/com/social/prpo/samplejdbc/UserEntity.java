package com.social.prpo.samplejdbc;

public class UserEntity extends Entity {

    private String username;

    public UserEntity(int id, String username) {
        this.setId(id);
        this.setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
