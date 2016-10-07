package com.sentinel.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    public String password;

    public User(Integer id, String firstName, String last_name, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = last_name;
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
