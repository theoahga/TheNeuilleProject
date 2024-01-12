package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ville", schema = "emergency")
public class City {
  @Id
  @Column(name = "id_ville")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "nom")
  private String nom;

  public City(String nom) {
    this.nom = nom;
  }

  public City() {
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Ville[id=" + this.id + " nom=" + this.nom + "]";
  }
}
