package com.socialbook.users.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_table")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u from user_table u"),
        @NamedQuery(name = "User.getOne", query = "SELECT u from user_table u where u.user_id = :id"),
        @NamedQuery(name = "User.getUserFriends",
                query = "SELECT user FROM user_table user WHERE user.user_id = :user_id"),
        @NamedQuery(name = "User.getUserByUsername", query = "SELECT u from user_table u WHERE u.username = :username")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "profileimg")
    private String profileImg;

    @Column(name = "firstname")
    private String name;

    @Column(name = "surname")
    private String surname;


    @OneToMany(fetch =  FetchType.LAZY)
    private List<User> friends;



    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = id;
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

    @Override
    public String toString() {
        return "{ \"name\": \"" + getUsername() + "\", \"name\": \"" + getName() + "\"}";
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
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
