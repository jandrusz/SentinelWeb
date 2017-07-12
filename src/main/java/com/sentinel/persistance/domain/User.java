package com.sentinel.persistance.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    public Integer id;

    public String firstName;

    public String lastName;

    public String login;

    public String password;

}
