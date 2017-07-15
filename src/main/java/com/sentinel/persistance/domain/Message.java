package com.sentinel.persistance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "messages")
public class Message {

    @Id
    @Column(name = "id_message")
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Integer idMessage;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "message")
    private String message;

    @Column(name = "read")
    private String read;

    @Column(name = "id_child")
    private Integer idChild;

    @Column(name = "time")
    private String time;

}
