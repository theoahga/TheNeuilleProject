package com.theoahga.model.sensor.api;

import org.locationtech.jts.geom.Coordinate;

public interface Sensor {
  String getId();

  Coordinate getCoordinate();

  String getAddress();

  SensorState getState();
}
