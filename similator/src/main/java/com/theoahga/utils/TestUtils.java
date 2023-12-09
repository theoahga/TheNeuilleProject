package com.theoahga.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.sensor.SensorImpl;
import com.theoahga.model.sensor.SensorFactory;
import com.theoahga.model.sensor.api.Sensor;
import org.locationtech.jts.geom.Coordinate;

import java.io.IOException;
import java.util.*;

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

  public static Map<String, List<Coordinate>> getSquareBounds() {

    // TODO remove and use endpoint from ermengency API
    double p1_x = Double.parseDouble(System.getProperty("square.bounds.p1.x"));
    double p1_y = Double.parseDouble(System.getProperty("square.bounds.p1.y"));
    Coordinate p1Coordinate = new Coordinate(p1_x, p1_y);

    double p2_x = Double.parseDouble(System.getProperty("square.bounds.p2.x"));
    double p2_y = Double.parseDouble(System.getProperty("square.bounds.p2.y"));
    Coordinate p2Coordinate = new Coordinate(p2_x, p2_y);

    Map<String, List<Coordinate>> map = new HashMap<>();
    map.put("villeurbanne", Arrays.asList(p1Coordinate, p2Coordinate));

    return map;
  }
}
