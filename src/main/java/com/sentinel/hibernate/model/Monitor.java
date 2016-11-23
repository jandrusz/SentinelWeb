package com.sentinel.hibernate.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "monitors")
public class Monitor {

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id")
    public Integer id;

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
