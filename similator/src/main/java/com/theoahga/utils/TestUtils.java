package com.theoahga.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.Sensor;
import com.theoahga.model.SensorCoordinate;
import com.theoahga.model.SensorFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

  public static List<Sensor> getTestSensors() {
    String sensors =
        "["
            + "{ \"id\":\"cid-1\","
            + "\"lat\" : 45,"
            + "\"lon\": 4 ,"
            + "\"address\" : \"Rue du test Unitaire\"},"
            + "{\"id\":\"cid-2\","
            + " \"lat\":46,"
            + " \"lon\":7,"
            + " \"address\":\"Chez JB\"}"
            + "]";

    List<Sensor> sensorList = new ArrayList<>();
    try {
      JsonNode jsonSensor = new ObjectMapper().readTree(sensors.getBytes());
      sensorList = SensorFactory.createSensorsFromJson(jsonSensor);
    } catch (ZeroException | IOException e) {
      System.out.println("Pas cool");
    }
    return sensorList;
  }
}
