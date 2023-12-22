package com.theoahga.simulationapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sensors_info", schema = "simulation")
public class SensorInfo {
    @Id
    @Column(name = "cid")
    private Long cid;

    @Column(name = "lat")
    private double lat;

    @Column(name = "lon")
    private double lon;

    @Column(name = "intensity")
    private int intensity;

    @Column(name = "type")
    private int type;

    public SensorInfo(Long cid, double lat, double lon, int intensity, int type) {
        this.cid = cid;
        this.lat = lat;
        this.lon = lon;
        this.intensity = intensity;
        this.type = type;
    }

    public SensorInfo() {

    }

    public Long getCid() {
        return cid;
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

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
