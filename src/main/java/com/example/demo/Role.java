package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role_table")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    @Column(name = "username")
    @Size(min=3)
    private String username;

    @Column(name = "role")
    private String role;

    public Role() {
    }

    public Role(@Size(min=3) String username, String role) {
        this.username = username;
        this.role = role;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }
}