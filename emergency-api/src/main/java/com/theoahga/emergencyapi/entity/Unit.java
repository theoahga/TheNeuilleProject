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
    private Boolean isAvailable;

    @ManyToOne
    private Station station;

    public Unit(List<FireType> specialities, Boolean isAvailable) {
        this.specialities = specialities;
        this.isAvailable = isAvailable;
    }

    public Unit() {

    }

    public Long getId() {
        return id;
    }


    public List<FireType> getSpecialities() {
        return specialities;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Station getStation() {
        return station;
    }
}
