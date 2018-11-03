package com.socialbook.users.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user_table")
@NamedQueries(value = {
        @NamedQuery(name = "User.getAll", query = "SELECT u from user_table u"),
        @NamedQuery(name = "User.getOne", query = "SELECT u from user_table u where u.user_id = :id"),
        @NamedQuery(name = "User.getFemales", query = "SELECT u from user_table u WHERE u.gender = :gender"),
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

    @Column(name = "gender")
    private String gender;


    @OneToMany(fetch =  FetchType.LAZY)
    private List<User> friends;

//    @JoinTable(name = "user_table_user_table", joinColumns = {
//            @JoinColumn(name = "user_id", referencedColumnName = "user_table_user_id", nullable = false)})
//    @ManyToMany(fetch = FetchType.LAZY)
//    private List<User> friends = new ArrayList<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id2")
//    private List<User> friends = new ArrayList<>();


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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
