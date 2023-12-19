package com.theoahga.model.sensor;

import com.fasterxml.jackson.databind.JsonNode;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.sensor.api.Sensor;
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
    String id = json.get("cid").asText();
    double lat = json.get("lat").asDouble();
    double lon = json.get("lon").asDouble();

    CheckUtils.notNull(id);
    CheckUtils.notNull(lat);
    CheckUtils.notNull(lon);

    return new SensorImpl(id, new SensorCoordinate(lat, lon), new SensorStateImpl());
  }
}
