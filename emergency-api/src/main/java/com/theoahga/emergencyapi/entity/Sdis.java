package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

@Entity
public class Sdis {
  @Id
  @Column(name = "id_sdis")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "nom")
  private String nom;

  @Column(name = "id_ville")
  private Long idVille;

  public Sdis(String nom, Long idVille) {
    this.nom = nom;
    this.idVille = idVille;
  }

  public Sdis() {}

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return "Sdis[id=" + this.id + " nom=" + this.nom + "  idVille=" + this.idVille + "]";
  }
}
