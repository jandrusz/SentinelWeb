package com.sentinel.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Table(name = "schedule_entries")
public class ScheduleEntry {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
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

	@Column(name = "id_area")
	public Integer idArea;

	public ScheduleEntry(String name, String timeStart, String timeStop, String day, Integer idSchedule, Integer idArea) {
		this.name = name;
		this.timeStart = timeStart;
		this.timeStop = timeStop;
		this.day = day;
		this.idSchedule = idSchedule;
		this.idArea = idArea;
	}

	public ScheduleEntry() {
	}
}
