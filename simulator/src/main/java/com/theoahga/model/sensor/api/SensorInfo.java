package com.theoahga.model.sensor.api;

public class SensorInfo{
    public Long cid;
    public double lat;
    public double lon;
    public int intensity;
    public int type;

    public SensorInfo(Long cid, double lat, double lon, int intensity, int type) {
        this.cid = cid;
        this.lat = lat;
        this.lon = lon ;
        this.intensity = intensity;
        this.type = type;
    }

    public Long getCid() {
        return cid;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public int getIntensity() {
        return intensity;
    }

    public int getType() {
        return type;
    }
}
