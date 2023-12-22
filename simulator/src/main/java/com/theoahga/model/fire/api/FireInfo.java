package com.theoahga.model.fire.api;

public class FireInfo {
    private final String id;
    private double lat;
    private double lon;
    private double radius;

    public FireInfo(String id, double lat, double lon, double radius) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
    }

    public String getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getRadius() {
        return radius;
    }
}
