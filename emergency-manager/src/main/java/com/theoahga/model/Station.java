package com.theoahga.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Station {
    private int id;
    private double lat;
    private double lon;
    private String address;
    private String name;
    private List<Unit> unitList;
    private List<Vehicle> vehicleList;

    public Station(int id, double lat, double lon, String address, String name) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.name = name;

        this.unitList = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void addUnit(Unit unit) {
        this.unitList.add(unit);
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicleList.add(vehicle);
    }

    public boolean hasUnitDispo() {
        return this.unitList.stream().anyMatch(i->i.isAvailable());
    }

    public boolean hasVehicleDispo() {
        return this.vehicleList.stream().anyMatch(i->i.isAvaible());
    }

    public Unit getAvailableUnit() {
        return this.unitList.stream().filter(i->i.isAvailable()).collect(Collectors.toList()).get(0);
    }

    public Vehicle getAvailableVehicle() {
        return this.vehicleList.stream().filter(i->i.isAvaible()).collect(Collectors.toList()).get(0);
    }
}
