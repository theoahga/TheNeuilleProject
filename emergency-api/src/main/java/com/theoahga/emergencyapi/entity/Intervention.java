package com.theoahga.emergencyapi.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(schema = "emergency")
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date endDate;
    @Column(name = "status")
    private Status status;
    @ManyToOne
    private Unit fireMenUnit;
    @ManyToMany
    private List<Vehicle> vehicles;

    public Intervention(Long id, Date startDate, Date endDate, Status status, Unit fireMenUnit, List<Vehicle> vehicles) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.fireMenUnit = fireMenUnit;
        this.vehicles = vehicles;
    }

    public Intervention() {

    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Status getStatus() {
        return status;
    }

    public Unit getFireMenUnit() {
        return fireMenUnit;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
