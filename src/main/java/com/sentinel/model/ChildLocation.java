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
@Table(name = "children_locations")
public class ChildLocation {

    @Id
    @Column(name = "id")
    public Integer id;

    @Column(name = "id_child")
    public Integer idChild;

    @Column(name = "id_location")
    public Integer idLocation;

    public ChildLocation() {
    }

    public ChildLocation(Integer id, Integer idChild, Integer idLocation) {
        this.id = id;
        this.idChild = idChild;
        this.idLocation = idLocation;
    }
}
