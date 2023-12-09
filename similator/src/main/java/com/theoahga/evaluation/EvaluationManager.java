package com.theoahga.evaluation;

import com.theoahga.model.sensor.api.Sensor;
import org.locationtech.jts.geom.Coordinate;

import java.util.*;

public class EvaluationManager {
  private SensorEvaluator sensorEvaluator;
  private FireEvaluator fireEvaluator;

  public EvaluationManager(List<Sensor> sensors, Map<String, List<Coordinate>> fireSquareBounds) {
    this.sensorEvaluator = new SensorEvaluator(sensors);
    this.fireEvaluator = new FireEvaluator(fireSquareBounds);
  }

  public Map<String, List<Object>> manage() {
    List<Sensor> changedSensors = sensorEvaluator.evaluate();
    List<Sensor> changedFires = fireEvaluator.evaluate();

    Map<String, List<Object>> map = new HashMap<>();
    map.put("changedsensors", Collections.singletonList(changedSensors));
    map.put("changedfires", Collections.singletonList(changedFires));

    return map;
  }

  public void publishChanges(Map<String, List<Object>> metaChanges) {
    if (!metaChanges.get("changedsensors").isEmpty()) {
      publishSensorChanges(metaChanges.get("changedsensors"));
    }

    if (!metaChanges.get("changedfires").isEmpty()) {
      publishFiresChanges(metaChanges.get("changedfires"));
    }
  }

  private void publishFiresChanges(List<Object> changedfires) {
    // TODO
  }

  private void publishSensorChanges(List<Object> changedfires) {
    // TODO
  }

  public void manageAndPublish() {
    publishChanges(manage());
  }
}
