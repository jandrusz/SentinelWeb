package com.sentinel.persistance;

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
    public String id;

    @Column(name = "email")
    public String email;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "password")
    public String password;

    public User(String id, String email, String firstName, String last_name, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = last_name;
        this.password = password;
    }

    public User() {
    }
}
