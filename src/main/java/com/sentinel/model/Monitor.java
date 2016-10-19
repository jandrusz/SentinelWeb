package com.sentinel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "monitors")
public class Monitor {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "id_user")
    public Integer idUser;

    @Column(name = "id_child")
    public Integer idChild;

    public Monitor(Integer id, Integer idUser, Integer idChild) {
        this.id = id;
        this.idUser = idUser;
        this.idChild = idChild;
    }

    public Monitor() {
    }
}
