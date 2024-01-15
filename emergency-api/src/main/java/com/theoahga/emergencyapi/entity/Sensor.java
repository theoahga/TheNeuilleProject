package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sensors", schema = "emergency")
public class Sensor {
  @Id
  @Column(name = "cid")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long cid;

  @Column(name = "lat")
  private double lat;

  @Column(name = "lon")
  private double lon;

  @Column(name = "adresse")
  private String adresse;

  @Column(name = "alias")
  private String alias;

  @Column(name = "intensity")
  private int intensity;


  @ManyToOne
  private City city;

  public Sensor(Long cid, double lat, double lon, String adresse, String alias,int intensity, City city) {
    this.cid = cid;
    this.lat = lat;
    this.lon = lon;
    this.adresse = adresse;
    this.alias = alias;
    this.intensity = intensity;
    this.city = city;
  }

  public Sensor() {
  }

  public Long getCid() {
    return cid;
  }

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) { this.lat = lat; }

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) { this.lon = lon; }

  public String getAdresse() {
    return adresse;
  }

  public void setAdresse(String addr) { this.adresse = addr; }

  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) { this.alias = alias; }

  public int getIntensity() { return intensity; }

  public void setIntensity(int intensity) { this.intensity = intensity; }

  public City getCity() { return city; }

  public void setCity(City city) { this.city = city; }

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
            + " intensité="
            + this.intensity
            + " idVille="
            + this.city.getId()
            + "]";
  }
}
