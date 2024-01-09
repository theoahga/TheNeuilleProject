package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "station", schema = "emergency")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "lat")
    private double lat;
    @Column(name = "lon")
    private double lon;
    @Column(name = "address")
    private String address;
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private Sdis sdis;

    public Station(double lat, double lon, String address, String name) {
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.name = name;
    }

    public Station() {

    }

    public Long getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Sdis getSdis() {
        return sdis;
    }
}
