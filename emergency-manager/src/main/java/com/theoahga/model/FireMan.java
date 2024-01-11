package com.theoahga.model;


public class FireMan {
    private Long registrationNumber;
    private String name;
    private String secondName;

    private Unit unit;

    public FireMan(Long registrationNumber, String name, String secondName, Unit unit) {
        this.registrationNumber = registrationNumber;
        this.name = name;
        this.secondName = secondName;
        this.unit = unit;
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

    public Unit getUnit() {
        return unit;
    }
}
