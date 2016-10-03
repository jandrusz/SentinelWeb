package com.sentinel.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "schedule_entries")
public class ScheduleEntry {

    @Column(name = "id")
    @Id
    public Integer id;

    @Column(name = "name")
    public String name;

    @Column(name = "time_start")
    public String timeStart;

    @Column(name = "time_stop")
    public String timeStop;

    @Column(name = "day")
    public String day;

    @Column(name = "id_schedule")
    public Integer idSchedule;

    @Column(name = "id_user")
    public Integer idUser;

    @Column(name = "id_area")
    public Integer idArea;

}
