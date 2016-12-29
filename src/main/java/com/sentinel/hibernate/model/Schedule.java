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
@Table(name = "schedules")
public class Schedule {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	public Integer id;

	@Column(name = "name")
	public String name;

	@Column(name = "id_user")
	public Integer idUser;

	public Schedule(String name, Integer idUser) {
		this.name = name;
		this.idUser = idUser;
	}

	public Schedule() {
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Schedule schedule = (Schedule) o;

		if (id != null ? !id.equals(schedule.id) : schedule.id != null) {
			return false;
		}
		if (name != null ? !name.equals(schedule.name) : schedule.name != null) {
			return false;
		}
		return idUser != null ? idUser.equals(schedule.idUser) : schedule.idUser == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
		return result;
	}
}
