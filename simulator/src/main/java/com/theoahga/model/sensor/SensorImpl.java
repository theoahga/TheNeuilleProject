package com.theoahga.model.sensor;

import com.theoahga.model.fire.api.TypeFire;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.model.sensor.api.SensorState;

public class SensorImpl implements Sensor {
  private final String id;
  private final SensorCoordinate coordinate;
  private SensorState state;

  public SensorImpl(
      String id, SensorCoordinate sensorCoordinate, SensorState sensorState) {
    this.id = id;
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

  @Override
  public void setIntensity(int intensity) {
    this.state.setIntensity(intensity);
  }

  @Override
  public void setType(TypeFire type) {
    this.state.setType(type);
  }

  @Override
  public void setState(SensorState state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "Sensor{"
        + "id='"
        + id
        + '\''
        + ", coordinate="
        + coordinate
        + ", state="
        + state
        + '}';
  }
}
