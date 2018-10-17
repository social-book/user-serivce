package com.socialbook.users.entities;

import javax.persistence.*;

@Entity(name = "user_table")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u from user_table u"),
        @NamedQuery(name = "User.getOne", query = "SELECT u from user_table u where u.id = :id"),
        @NamedQuery(name = "User.getFemales", query = "SELECT u from user_table u WHERE u.gender = :gender")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private String gender;


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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {

        return "{ \"name\": \"" + getUsername() + "\", \"gender\": \"" + getGender() + "\"}";

    }
}
