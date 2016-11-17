package com.sentinel.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "schedules")
public class Schedule {

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "id_user")
    public Integer idUser;

    public Schedule(String name, Integer idUser) {
        this.name = name;
        this.idUser = idUser;
    }

    public Schedule() {
    }
}
