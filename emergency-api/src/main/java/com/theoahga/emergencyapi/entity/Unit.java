package com.theoahga.emergencyapi.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "unit", schema = "emergency")
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToMany
    private List<FireType> specialities;
    @Column(name = "isAvailable")
    private Boolean available;

    @ManyToOne
    private Station station;

    public Unit(List<FireType> specialities, Boolean available) {
        this.specialities = specialities;
        this.available = available;
    }

    public Unit() {

    }

    public Unit(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public List<FireType> getSpecialities() {
        return specialities;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Station getStation() {
        return station;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpecialities(List<FireType> specialities) {
        this.specialities = specialities;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
