package com.sentinel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "schedules")
public class Schedule {

    @Column(name = "id")
    @Id
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "id_user")
    public Integer idUser;

    public Schedule(Integer id, String name, Integer idUser) {
        this.id = id;
        this.name = name;
        this.idUser = idUser;
    }

    public Schedule() {
    }
}
