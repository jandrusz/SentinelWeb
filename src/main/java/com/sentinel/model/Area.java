package com.sentinel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@Table(name = "areas")
public class Area {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "longitude")
    public Double longitude;

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "radius")
    public Double radius;

    public Area() {
    }

    public Area(Integer id, Double longitude, Double latitude, Double radius) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
    }
}
