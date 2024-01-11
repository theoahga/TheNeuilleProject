package com.theoahga.evaluation;

import com.theoahga.http.EmergencyHttp;
import com.theoahga.model.*;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.Coordinate;
import org.opengis.referencing.operation.TransformException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmergencyEvaluator {

    private List<Sdis> sdisList;

    public EmergencyEvaluator(){

        try{
            this.sdisList = EmergencyHttp.getSdisDefinition();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


    public void evaluate(){
        List<Intervention> startedInterventions = EmergencyHttp.getStartedInterventions();

        List<Integer> treatSensorIds = new ArrayList<>();
        for(Intervention inter : startedInterventions){
            SensorEvolution sensorEvolution = EmergencyHttp.getSensorEvolutionBySensorId(inter.getCid());
            if(sensorEvolution.off()){
                inter.close();
                inter.publish();

                treatSensorIds.add(inter.getCid());
            }else if(sensorEvolution.increase() || sensorEvolution.isStable()){
                // Add
                inter.publish();

                treatSensorIds.add(inter.getCid());
            }else if(sensorEvolution.decrease()){
                // Nothing To Do
            }
        }


        List<Sensor> sensors = EmergencyHttp.getActiveSensors();
        for (Sensor sensor : sensors){
            if(!treatSensorIds.contains(sensor.getCid())){
                Intervention intervention = new Intervention(sensor.getCid());
                affectVehiculeAndUnit(intervention,sensor);
                intervention.publish();
            }
        }
    }

    private void affectVehiculeAndUnit(Intervention intervention,Sensor sensor){
        List<Station> stationsOrdered = getStationsOrderedByDistance(sensor);

        Station selectedStation = null ;
        for(Station station : stationsOrdered){
            if(station.hasUnitDispo() && station.hasVehicleDispo()){
                selectedStation = station;
                break;
            }
        }

        if(selectedStation != null){
            affectVehicle(selectedStation, intervention);
            affectUnit(selectedStation, intervention);
        }
    }

    private List<Station> getStationsOrderedByDistance(Sensor sensor) {
        Sdis sdis = this.sdisList.stream()
                .filter(i -> i.getCityId() == sensor.getCityId())
                .findFirst()
                .orElse(null);

        if (sdis == null) {
            return Collections.emptyList();
        }

        Coordinate sensorCoordinate = new Coordinate(sensor.getLon(), sensor.getLat());
        return sdis.getStations().stream()
                .sorted(new Comparator<Station>() {
                    @Override
                    public int compare(Station o1, Station o2) {
                        Coordinate s1 = new Coordinate(o1.getLon(), o1.getLat());
                        Coordinate s2 = new Coordinate(o2.getLon(), o2.getLat());

                        Double distance1, distance2;

                        try {
                            distance1 = JTS.orthodromicDistance(s1, sensorCoordinate, DefaultGeographicCRS.WGS84);
                            distance2 = JTS.orthodromicDistance(s2, sensorCoordinate, DefaultGeographicCRS.WGS84);
                        } catch (TransformException e) {
                            throw new RuntimeException(e);
                        }

                        return Double.compare(distance1, distance2);
                    }
                })
                .collect(Collectors.toList());
    }


    private void affectUnit(Station station, Intervention intervention) {
        Unit unitToAffect = station.getAvailableUnit();
        unitToAffect.setAvailable(false);
        unitToAffect.publish();

        intervention.addUnit(unitToAffect);
    }

    private void affectVehicle(Station station, Intervention intervention) {
        Vehicle vehicleToAffect = station.getAvailableVehicle();
        vehicleToAffect.setAvailable(false);
        vehicleToAffect.publish();

        intervention.addVehicle(vehicleToAffect);
    }


}