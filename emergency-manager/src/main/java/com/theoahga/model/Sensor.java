package com.theoahga.model;


public class Sensor {
  private int cid;

  private double lat;

  private double lon;

  private String adresse;

  private String alias;

  private int cityId;

  private int intensity;

  private String type; // TODO :: implement the unit/vehicle choice with fire type

  public Sensor(int cid, double lat, double lon, int intensity, String type) {
    this.cid = cid;
    this.lat = lat;
    this.lon = lon;
    this.intensity = intensity;
    this.type = type;
  }

  public int getCid() {
    return cid;
  }

  public double getLat() {
    return lat;
  }

  public double getLon() {
    return lon;
  }

  public String getAdresse() {
    return adresse;
  }

  public String getAlias() {
    return alias;
  }

  public int getCityId() {
    return cityId;
  }

  @Override
  public String toString() {
    return "Sensor[cid="
            + this.cid
            + " alias="
            + this.alias
            + " lat="
            + this.lat
            + " lon="
            + this.lon
            + " adresse="
            + this.adresse
            + "]";
  }
}
