package com.theoahga.evaluation;

import com.theoahga.exception.DistanceComputingException;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.sensor.api.Sensor;
import org.locationtech.jts.geom.Coordinate;

import java.util.*;

public class EvaluationManager {
  private SensorEvaluator sensorEvaluator;
  private FireEvaluator fireEvaluator;
  private List<Fire> fires = new ArrayList<>();

  public EvaluationManager(List<Sensor> sensors, Map<String, List<Coordinate>> fireSquareBounds) {
    this.sensorEvaluator = new SensorEvaluator(sensors, fires);
    this.fireEvaluator = new FireEvaluator(fireSquareBounds,fires);
  }

  public Map<String, List<Object>> manage() {
    Set<Object> changedSensors = sensorEvaluator.evaluate();
    Set<Object> changedFires = fireEvaluator.evaluate();

    Map<String, List<Object>> map = new HashMap<>();
    map.put("changedsensors", Collections.singletonList(changedSensors));
    map.put("changedfires", Collections.singletonList(changedFires));

    for(Object obj : changedSensors){
      Sensor sensor = (Sensor) obj;
      System.out.println(sensor);
    }

    for(Object obj : changedFires){
      Fire fire = (Fire) obj;
      System.out.println(fire);
    }

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
