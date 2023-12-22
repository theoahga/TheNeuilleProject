package com.theoahga.model.fire;

import com.theoahga.model.fire.api.Fire;
import org.geotools.measure.Measure;
import org.locationtech.jts.geom.Coordinate;

import java.util.UUID;

public class FireImpl implements Fire {
  private FireCoordinate coordinate;
  private Measure radius;
  private final UUID uuid;

  public FireImpl(FireCoordinate coordinate, Measure radius) {
    this.coordinate = coordinate;
    this.radius = radius;
    this.uuid = UUID.randomUUID();
  }

  @Override
  public FireCoordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public Measure getRadius() {
    return radius;
  }

  @Override
  public void setRadius(Measure radius1) {
    this.radius = radius1;
  }

  @Override
  public String getId() {
    return uuid.toString();
  }

  @Override
  public String toString() {
    return "Fire{"
            + "coordinate="
            + coordinate
            + ", radius="
            + radius
            + '}';
  }
}
