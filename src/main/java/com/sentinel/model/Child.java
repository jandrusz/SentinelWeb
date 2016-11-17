package com.sentinel.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "children")
public class Child {

    @Id
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    public Integer id;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "login")
    public String login;

    @Column(name = "password")
    public String password;

    @Column(name = "id_schedule")
    public Integer idSchedule;

    public Child(String firstName, String lastName, String login, String password, Integer idSchedule) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.idSchedule = idSchedule;
    }

    public Child() {
    }
}
