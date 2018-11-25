package com.socialbook.users.services;

import java.util.List;

public class UserDto {
    private Integer userId;
    private String username;
    private String password;
    private String imgref;
    private String name;
    private String surname;
    private List<UserDto> friends;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserDto> getFriends() {
        return friends;
    }

    public void setFriends(List<UserDto> friends) {
        this.friends = friends;
    }

    public String getImgref() {
        return imgref;
    }

    public void setImgref(String imgref) {
        this.imgref = imgref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
