package com.sentinel.persistance;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "monitors")
public class Monitor {

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
