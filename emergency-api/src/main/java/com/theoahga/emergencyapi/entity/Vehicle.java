package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "vehicle", schema = "emergency")
public class Vehicle {
    @Id
    @Column(name = "immat", nullable = false)
    private String immat;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "placesNumber")
    private int placesNumber;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<FireType> associatedFireTypes;
    @Column(name = "type")
    private VehicleType type;
    @Column(name = "isAvailable")
    private boolean isAvailable;

    @ManyToOne
    private Station station;

    public Vehicle(String immat, String name, String description, int placesNumber, List<FireType> associatedFireTypes, VehicleType type) {
        this.immat = immat;
        this.name = name;
        this.description = description;
        this.placesNumber = placesNumber;
        this.associatedFireTypes = associatedFireTypes;
        this.type = type;
    }

    public Vehicle() {
    }

    public String getImmat() {
        return immat;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public List<FireType> getAssociatedFireTypes() {
        return associatedFireTypes;
    }

    public VehicleType getType() {
        return type;
    }

    public Station getStation() {
        return station;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
