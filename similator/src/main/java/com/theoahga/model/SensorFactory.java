package com.theoahga.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.theoahga.exception.ZeroException;
import com.theoahga.utils.CheckUtils;

import java.util.ArrayList;
import java.util.List;

public class SensorFactory {
  public static List<Sensor> createSensorsFromJson(JsonNode json) throws ZeroException {
    List<Sensor> sensors = new ArrayList<>();

    CheckUtils.notZero(json.size());

    for (int i = 0; i < json.size(); i++) {
      sensors.add(SensorFactory.createSensorFromJson(json.get(i)));
    }

    return sensors;
  }

  public static Sensor createSensorFromJson(JsonNode json) {
    String id = json.get("id").asText();
    int lat = json.get("lat").asInt();
    int lon = json.get("lon").asInt();
    String address = json.get("address").asText();

    CheckUtils.notNull(id);
    CheckUtils.notNull(lat);
    CheckUtils.notNull(lon);
    CheckUtils.notNull(address);

    return new Sensor(id, address, new SensorCoordinate(lat, lon), new SensorState());
  }
}
