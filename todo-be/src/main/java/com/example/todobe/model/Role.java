package com.example.todobe.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="\"role\"")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Integer roleId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
