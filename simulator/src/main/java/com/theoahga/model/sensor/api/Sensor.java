package com.theoahga.model.sensor.api;

import com.theoahga.model.fire.api.TypeFire;
import org.locationtech.jts.geom.Coordinate;

public interface Sensor {
  String getId();

  Coordinate getCoordinate();

  String getAddress();

  SensorState getState();
  void setIntensity(int intensity);

  void setType(TypeFire type);

  void setState(SensorState state);
}
