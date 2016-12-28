package com.sentinel.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Setter
@Getter
@Table(name = "locations")
public class Location {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
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

	@Column(name = "id_child")
	public Integer idChild;

	public Location() {
	}

	public Location(Double longitude, Double latitude, String day, String time, Integer idChild) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.day = day;
		this.time = time;
		this.idChild = idChild;
	}
}
