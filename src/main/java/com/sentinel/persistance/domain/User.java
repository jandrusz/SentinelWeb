package com.sentinel.persistance.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Users")
public class User {

    @Id
    @Column(name = "id_user")
    public Integer idUser;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    public String login;

    public String password;

}
