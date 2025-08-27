package com.example.todobe.model;

import jakarta.persistence.*;

@Table(name="\"user\"")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private Integer userId;

    @Column(name="username", nullable = false, length = 20)
    private String username;

    @Column(name="password", nullable = false, length = 200)
    private String password;

    @Column(name="email", nullable = false, length = 100)
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
