package com.socialbook.users.entities;

import javax.persistence.*;

@Entity(name = "user")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u from user u"),
        @NamedQuery(name = "User.getFriends", query = "SELECT u from user u"),
        @NamedQuery(name = "User.getFriend", query = "SELECT u from user u")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "user_id")
//    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
