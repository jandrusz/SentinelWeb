package com.sentinel.hibernate.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "id_message")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    public Integer idMessage;

    @Column(name = "id_user")
    public Integer idUser;

    @Column(name = "message")
    public String message;

    @Column(name = "read")
    public String read;

    @Column(name = "id_child")
    public Integer idChild;

    @Column(name = "time")
    public String time;

    public Message() {
    }

    public Message(Integer idUser, String message, String read, Integer idChild, String time) {
        this.idUser = idUser;
        this.message = message;
        this.read = read;
        this.idChild = idChild;
        this.time = time;
    }
}
