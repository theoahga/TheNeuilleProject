package com.theoahga.model;

import com.theoahga.http.EmergencyHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String immat;
    private String name;
    private String description;
    private int placesNumber;
    private List<FireType> associatedFireTypes;
    private String type;
    private boolean isAvaible;

    public Vehicle(String immat, String name, String description, int placesNumber, String type, Boolean isAvaible) {
        this.immat = immat;
        this.name = name;
        this.description = description;
        this.placesNumber = placesNumber;
        this.associatedFireTypes = new ArrayList<>();
        this.type = type;
        this.isAvaible = isAvaible;
    }

    public Vehicle() {
    }

    public String getImmat() {
        return immat;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public List<FireType> getAssociatedFireTypes() {
        return associatedFireTypes;
    }

    public String getType() {
        return type;
    }
    public void addTypes(FireType type){
        this.associatedFireTypes.add(type);
    }

    public boolean isAvaible() {
        return isAvaible;
    }

    public void setAvailable(boolean b) {
        this.isAvaible = b;
    }

    public void publish() {
        String url = System.getProperty("emergency.api.host") + System.getProperty("emergency.api.endpoint.vehicle.update");

        try {
            EmergencyHttp.sendPostRequest(url, this);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
