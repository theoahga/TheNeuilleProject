package com.theoahga.model.fire;

import org.locationtech.jts.geom.Coordinate;

public class FireCoordinate extends Coordinate {
  private final double longitude;
  private final double lattitude;

  public FireCoordinate(double longitude, double lattitude) {
    super(longitude, lattitude);
    this.longitude = longitude;
    this.lattitude = lattitude;
  }
}
