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

  @Column(name = "id_ville")
  private Long idVille;

  public Sensor(Long cid, double lat, double lon, String adresse, String alias, Long idVille) {
    this.cid = cid;
    this.lat = lat;
    this.lon = lon;
    this.adresse = adresse;
    this.alias = alias;
    this.idVille = idVille;
  }

  public Sensor() {}

  public Long getCid() {
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

  public Long getIdVille() {
    return idVille;
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
        + " idVille="
        + this.idVille
        + "]";
  }
}
