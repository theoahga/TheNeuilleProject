package com.theoahga.model;


import java.util.ArrayList;
import java.util.List;

public class Sdis {
  private int id;
  private String nom;
  private List<Station> stations;
  private int cityId;

  public Sdis(int id, String nom, int cityId) {
    this.id = id;
    this.nom = nom;
    this.stations = new ArrayList<>();
    this.cityId = cityId;
  }

  public Sdis() {
  }

  public int getId() {
    return id;
  }

  public void addStations(Station station){
    stations.add(station);
  }

  public List<Station> getStations() {
    return stations;
  }

  public int getCityId() {
    return cityId;
  }

  @Override
  public String toString() {
    return "SdisRepository[id=" + this.id + " nom=" + this.nom+ "]";
  }
}
