package com.theoahga.evaluation;

import com.theoahga.evaluation.api.Evaluator;
import com.theoahga.model.sensor.SensorCoordinate;
import com.theoahga.model.sensor.api.Sensor;

import java.util.ArrayList;
import java.util.List;

public class SensorEvaluator implements Evaluator {
  private List<Sensor> sensors;

  public SensorEvaluator(List<Sensor> sensors) {
    this.sensors = sensors;
  }

  @Override
  public List<Sensor> evaluate() {
    return processAllSensors();
  }

  public List<Sensor> processAllSensors() {
    List<Sensor> modifiedSensors = new ArrayList<>();
    for (Sensor sensor : sensors) {
      Sensor modifiedSensor = process(sensor);
      if (modifiedSensor != null) {
        modifiedSensors.add(modifiedSensor);
      }
    }
    return modifiedSensors;
  }

  private Sensor process(Sensor sensor) {

    return null;
  }
}
