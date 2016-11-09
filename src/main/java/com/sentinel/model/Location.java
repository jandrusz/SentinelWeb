package com.sentinel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name = "locations")
public class Location {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "longitude")
    public Double longitude;

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "day")
    public String day;

    @Column(name = "time")
    public String time;

    public Location() {
    }

    public Location(Integer id, Double longitude, Double latitude, String day, String time) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.day = day;
        this.time = time;
    }
}
