package com.theoahga.model.sensor.api;

import com.theoahga.model.fire.api.TypeFire;

public interface SensorState {
  int getIntensity();

  TypeFire getType();

  void setType(TypeFire type);

  void setIntensity(int intensity);
}
