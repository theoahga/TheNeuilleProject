package com.theoahga.model;

import java.net.URI;
import java.net.http.HttpRequest;

public class Sensor {
  private final String id;
  private final String address;
  private final SensorCoordinate coordinate;
  private SensorState state;

  public Sensor(
      String id, String address, SensorCoordinate sensorCoordinate, SensorState sensorState) {
    this.id = id;
    this.address = address;
    this.coordinate = sensorCoordinate;
    this.state = sensorState;
  }

  public String getId() {
    return id;
  }

  public SensorCoordinate getCoordinate() {
    return coordinate;
  }

  public SensorState getState() {
    return state;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return "Sensor{"
        + "id='"
        + id
        + '\''
        + ", address='"
        + address
        + '\''
        + ", coordinate="
        + coordinate
        + ", state="
        + state
        + '}';
  }
}
