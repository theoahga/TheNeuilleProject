package com.theoahga.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.http.EmergencyHttp;
import com.theoahga.mixin.UnitMixIn;
import com.theoahga.mixin.VehicleMixIn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Intervention {
    private int id;
    private Date startDate;
    private Date endDate;
    private Status status;
    private List<Unit> units;
    private List<Vehicle> vehicles;
    private int cid;

    public Intervention(int id, Date startDate, Date endDate, Status status, List<Unit> units, List<Vehicle> vehicles, int cid) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.units = units;
        this.vehicles = vehicles;
        this.cid = cid;
    }

    public Intervention(int cid) {
        this.id = -1;
        this.startDate = new Date();
        this.status = Status.PROGRESSING;
        this.cid = cid;

        this.units = new ArrayList<>();
        this.vehicles = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public int getCid() {
        return cid;
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

    public List<Unit> getUnits() {
        return units;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void close(){
        this.status = Status.FINISHED;
        this.endDate = new Date();
    }

    public void publish() {
        String url = System.getProperty("emergency.api.host") ;
        if(getId() == -1){
            url += System.getProperty("emergency.api.endpoint.intervention.create");
        }else{
            url += System.getProperty("emergency.api.endpoint.intervention.update");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper()
                    .addMixIn(Vehicle.class, VehicleMixIn.class)
                    .addMixIn(Unit.class, UnitMixIn.class);

            String requestBody = objectMapper.writeValueAsString(this);
            EmergencyHttp.sendPostRequest(url, requestBody);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUnit(Unit availableUnit) {
        this.units.add(availableUnit);
    }

    public void addVehicle(Vehicle vehicleToAffect) {
        this.vehicles.add(vehicleToAffect);
    }
}
