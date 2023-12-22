package com.theoahga.simulationapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fires_info", schema = "simulation")
public class FireInfo {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;

    @Column(name = "radius")
    private Double radius;

    public FireInfo(String id, double lat, double lon, Double radius) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
    }

    public FireInfo() {
    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }
}
