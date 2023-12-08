package com.theoahga.model;

public class SensorCoordinate {
  private final float lattitude;
  private final float longitude;

  public SensorCoordinate(float lattitude, float longitude) {
    this.longitude = longitude;
    this.lattitude = lattitude;
  }

  public float getLattitude() {
    return lattitude;
  }

  public float getLongitude() {
    return longitude;
  }
}
