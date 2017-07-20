package com.sentinel.persistance.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "schedules")
@Table(name = "schedules")
public class Schedule {

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = "id_schedule")
    private Integer idSchedule;

    @Column(name = "name")
    private String name;

    @Column(name = "id_user")
    private Integer idUser;
}
