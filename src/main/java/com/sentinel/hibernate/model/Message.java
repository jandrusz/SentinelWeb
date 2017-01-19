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
    @Column(name = "id")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    public Integer id;

    @Column(name = "text_from")
    public String textFrom;

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

    public Message(String textFrom, String message, String read, Integer idChild, String time) {
        this.textFrom = textFrom;
        this.message = message;
        this.read = read;
        this.idChild = idChild;
        this.time = time;
    }
}
