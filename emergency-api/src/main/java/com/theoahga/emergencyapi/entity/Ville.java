package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "ville", schema = "emergency")
public class Ville {
  @Id
  @Column(name = "id_ville")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "nom")
  private String nom;

  public Ville(String nom) {
    this.nom = nom;
  }

  public Ville() {}

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Ville[id=" + this.id + " nom=" + this.nom + "]";
  }
}
