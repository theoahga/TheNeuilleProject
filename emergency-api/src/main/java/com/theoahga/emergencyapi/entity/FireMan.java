package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fireman", schema = "emergency")
public class FireMan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "registration_number", nullable = false)
    private Long registrationNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "second_name")
    private String secondName;

    @ManyToOne
    private Unit unit;

    public FireMan(String name, String secondName) {
        this.name = name;
        this.secondName = secondName;
    }

    public FireMan() {

    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}

