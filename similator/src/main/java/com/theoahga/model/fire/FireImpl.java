package com.theoahga.model.fire;

import com.theoahga.model.fire.api.Fire;
import org.geotools.measure.Measure;
import org.locationtech.jts.geom.Coordinate;

public class FireImpl implements Fire {
  private FireCoordinate coordinate;
  private Measure radius;

  public FireImpl(FireCoordinate coordinate, Measure radius) {
    this.coordinate = coordinate;
    this.radius = radius;
  }

  @Override
  public FireCoordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public Measure getRadius() {
    return radius;
  }
}
