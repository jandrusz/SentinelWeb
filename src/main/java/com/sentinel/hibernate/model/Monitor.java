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
@Getter
@Setter
@Table(name = "monitors")
public class Monitor {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id_monitor")
	public Integer idMonitor;

	@Column(name = "id_user")
	public Integer idUser;

	@Column(name = "id_child")
	public Integer idChild;

	public Monitor(Integer idUser, Integer idChild) {
		this.idUser = idUser;
		this.idChild = idChild;
	}

	public Monitor() {
	}
}
