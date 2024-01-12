package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sdis", schema = "emergency")
public class Sdis {
  @Id
  @Column(name = "id_sdis")
  private Long id;

  private String nom;

  @ManyToOne(fetch = FetchType.EAGER)
  private City city;


  public Sdis(String nom, City city) {
    this.nom = nom;
    this.city = city;
  }

  public Sdis() {
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "SdisRepository[id=" + this.id + " nom=" + this.nom + "  idVille=" + this.city.getId() + "]";
  }

  public String getNom() {
    return nom;
  }

  public City getCity() {
    return city;
  }
}
