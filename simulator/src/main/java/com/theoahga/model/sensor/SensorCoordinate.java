package com.theoahga.model.sensor;

import org.locationtech.jts.geom.Coordinate;

public class SensorCoordinate extends Coordinate {
  private final Double lattitude;
  private final Double longitude;

  public SensorCoordinate(Double lattitude, Double longitude) {
    super(longitude, lattitude);
    this.longitude = longitude;
    this.lattitude = lattitude;
  }
}
