package com.theoahga.evaluation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.exception.PostRequestException;
import com.theoahga.http.SimulatorHttp;
import com.theoahga.model.fire.api.Fire;
import com.theoahga.model.fire.api.FireInfo;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.utils.ParsingUtils;
import com.theoahga.model.sensor.api.SensorInfo;
import org.locationtech.jts.geom.Coordinate;

import java.io.IOException;
import java.util.*;

public class EvaluationManager {
  private SensorEvaluator sensorEvaluator;
  private FireEvaluator fireEvaluator;
  private List<Fire> fires = new ArrayList<>();
  private final ObjectMapper objectMapper;

  private Set<Object> changedSensors;
  private Set<Object> changedFires;

  public EvaluationManager(List<Sensor> sensors, Map<String, List<Coordinate>> fireSquareBounds) {
    this.sensorEvaluator = new SensorEvaluator(sensors, fires);
    this.fireEvaluator = new FireEvaluator(fireSquareBounds,fires);

    this.objectMapper = new ObjectMapper();
  }

  public void manage() {
    changedSensors = sensorEvaluator.evaluate();
    changedFires = fireEvaluator.evaluate();

    System.out.println("SensorEvaluator : " + changedSensors.size() + " sensors has changed. There are " + sensorEvaluator.getSensors().size() + " sensors in total");
    System.out.println("FireEvaluator : " + changedFires.size() + " sensors has changed. There are " + fireEvaluator.getFires().size() + " fires in total");
  }

  public void publishChanges() {
    try{
      if (!changedSensors.isEmpty()) {
        publishSensorChanges(changedSensors);
      }

      if (!changedFires.isEmpty()) {
        publishFiresChanges(changedFires);
      }
    }catch(PostRequestException p){
      p.printStackTrace();
    }

  }

  private void publishFiresChanges(Set<Object> changedfires) throws PostRequestException {
    if (changedfires.size() == 0){
      return;
    }

    String url =
            System.getProperty("simulator.api.host")
                    + System.getProperty("simulator.api.endpoint.updatefire");

    List<FireInfo> fireInfos = new ArrayList<>();
    for (Object fire : changedfires){
      fireInfos.add(ParsingUtils.FireToFireInfo((Fire) fire));
    }

    try {
      String jsonBody = objectMapper.writeValueAsString(fireInfos);
      SimulatorHttp.sendPostRequest(url, jsonBody);
    } catch (IOException | InterruptedException e) {
      throw new PostRequestException(url);
    }
  }

  private void publishSensorChanges(Set<Object> changedSensor) throws PostRequestException {
    if (changedSensor.size() == 0){
      return;
    }

    String url =
            System.getProperty("simulator.api.host")
                    + System.getProperty("simulator.api.endpoint.updatesensor");

    List<SensorInfo> sensorInfos = new ArrayList<>();
    for (Object sensor : changedSensor){
      sensorInfos.add(ParsingUtils.SensorToSensorInfo((Sensor) sensor));
    }

    try {
      String jsonBody = objectMapper.writeValueAsString(sensorInfos);
      SimulatorHttp.sendPostRequest(url, jsonBody);
    } catch (IOException | InterruptedException e) {
      throw new PostRequestException(url);
    }
  }

  public void manageAndPublish() {
    manage();
    publishChanges();
  }
}
