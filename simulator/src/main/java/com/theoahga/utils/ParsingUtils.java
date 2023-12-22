package com.theoahga.utils;

import com.theoahga.model.fire.api.FireInfo;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.model.sensor.api.SensorInfo;

public class ParsingUtils {

    public static SensorInfo SensorToSensorInfo(Sensor sensor){

        SensorInfo info = new SensorInfo(
                Long.parseLong(sensor.getId()),
                sensor.getCoordinate().getY(),
                sensor.getCoordinate().getX(),
                sensor.getState().getIntensity(),
                sensor.getState().getType().getAssociatedInt());
        return info ;
    }
    public static FireInfo FireToFireInfo(Fire fire){
        FireInfo info = new FireInfo(
                fire.getId(),
                fire.getCoordinate().getX(),
                fire.getCoordinate().getY(),
                fire.getRadius().doubleValue());
        return info ;
    }
}
