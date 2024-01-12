package com.theoahga.emergencyapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "emergency")
public class FireType {
    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "number")
    private int number;

    public FireType(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public FireType() {

    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
