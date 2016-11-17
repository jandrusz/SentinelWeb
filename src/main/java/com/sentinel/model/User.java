package com.sentinel.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
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

    public User(String firstName, String last_name, String login, String password) {
        this.firstName = firstName;
        this.lastName = last_name;
        this.login = login;
        this.password = password;
    }

    public User() {
    }
}
