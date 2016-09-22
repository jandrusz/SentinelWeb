package com.sentinel.persistance;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "monitors")
public class Monitor {

    @Id
    @Column(name = "id_user")
    public Integer idUser;

    @Column(name = "id_child")
    public Integer idChild;

    public Monitor(Integer idUser, Integer idChild) {
        this.idUser = idUser;
        this.idChild = idChild;
    }

    public Monitor() {
    }
}
