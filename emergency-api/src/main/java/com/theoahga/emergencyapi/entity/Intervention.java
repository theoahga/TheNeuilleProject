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
    @Column(name = "cid")
    private Integer cid;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "endDate")
    private Date endDate;
    @Column(name = "status")
    private Status status;
    @ManyToMany
    private List<Unit> units;

    @ManyToMany
    private List<Vehicle> vehicles;

    public Intervention(Long id, int cid, Date startDate, Date endDate, Status status, List<Unit> units, List<Vehicle> vehicles) {
        this.id = id;
        this.cid = cid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.units = units;
        this.vehicles = vehicles;
    }

    public Intervention(int cid, Date startDate, Date endDate, Status status, List<Unit> units, List<Vehicle> vehicles) {
        this.id = id;
        this.cid = cid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.units = units;
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

    public List<Unit> getFireMenUnit() {
        return units;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
