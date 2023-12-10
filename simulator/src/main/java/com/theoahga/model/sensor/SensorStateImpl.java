package com.theoahga.model.sensor;

import com.theoahga.model.sensor.api.SensorState;
import com.theoahga.model.fire.api.TypeFire;

public class SensorStateImpl implements SensorState {
  private int intensity;
  private TypeFire type;

  public SensorStateImpl() {
    this.intensity = 0;
    this.type = null;
  }

  @Override
  public int getIntensity() {
    return intensity;
  }

  @Override
  public TypeFire getType() {
    return type;
  }

  @Override
  public void setType(TypeFire type) {
    this.type = type;
  }

  @Override
  public void setIntensity(int intensity) {
    this.intensity = intensity;
  }
}
