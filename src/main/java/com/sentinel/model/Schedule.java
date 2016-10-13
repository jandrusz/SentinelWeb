package com.sentinel.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "schedules")
public class Schedule {

    @Column(name = "id")
    @Id
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "id_user")
    public Integer idUser;


}
