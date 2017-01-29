package com.sentinel.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@Entity
@Table(name = "areas")
public class Area {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id_area")
	public Integer idArea;

	@Column(name = "longitude")
	public Double longitude;

	@Column(name = "latitude")
	public Double latitude;

	@Column(name = "radius")
	public Float radius;

	public Area() {
	}

	public Area(Double longitude, Double latitude, Float radius) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.radius = radius;
	}
}
