package com.sentinel.persistance.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "schedule_entries")
public class ScheduleEntry {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id_schedule_entry")
	private Integer idScheduleEntry;

	@Column(name = "name")
	private String name;

	@Column(name = "time_start")
	private String timeStart;

	@Column(name = "time_stop")
	private String timeStop;

	@Column(name = "day_of_week")
	private String dayOfWeek;

	@Column(name = "id_schedule")
	private Integer idSchedule;

	@Column(name = "id_area")
	private Integer idArea;

}
