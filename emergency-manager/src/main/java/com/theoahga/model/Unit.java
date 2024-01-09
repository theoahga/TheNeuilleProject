package com.theoahga.model;


import com.theoahga.http.EmergencyHttp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Unit {
    private int id;
    private List<FireMan> fireMen;
    private List<FireType> specialities;
    private Boolean isAvailable;

    public Unit(int id,Boolean isAvailable) {
        this.id = id;
        this.isAvailable = isAvailable;

        this.fireMen = new ArrayList<>();
        this.specialities = new ArrayList<>();
    }


    public int getId() {
        return id;
    }

    public List<FireMan> getFireMen() {
        return fireMen;
    }

    public List<FireType> getSpecialities() {
        return specialities;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }

    public void addSpecility(FireType type){
        this.specialities.add(type);
    }

    public void setAvailable(boolean b) {
        this.isAvailable = b;
    }

    public void publish() {
        String url = System.getProperty("emergency.api.host") + System.getProperty("emergency.api.endpoint.unit.update");

        try {
            EmergencyHttp.sendPostRequest(url, this);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
