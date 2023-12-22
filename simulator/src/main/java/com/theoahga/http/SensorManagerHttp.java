package com.theoahga.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.exception.GetRequestException;
import com.theoahga.exception.PostRequestException;
import com.theoahga.model.sensor.SensorFactory;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.utils.ParsingUtils;
import com.theoahga.model.sensor.api.SensorInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SensorManagerHttp {

  private static ObjectMapper objectMapper = new ObjectMapper();
  public static List<Sensor> getSensor() throws GetRequestException, ZeroException {
    String url =
        System.getProperty("simulator.api.host")
            + System.getProperty("simulator.api.endpoint.getallsensor");

    JsonNode json;
    try {
      json = (JsonNode) SimulatorHttp.sendGetRequest(url);
    } catch (IOException | InterruptedException e) {
      throw new GetRequestException(url);
    }

    return SensorFactory.createSensorsFromJson(json);
  }

  public static JsonNode initSensorInfo(List<Sensor> sensors) throws PostRequestException {
    String url =
            System.getProperty("simulator.api.host")
                    + System.getProperty("simulator.api.endpoint.init");

    List<SensorInfo> sensorInfo = new ArrayList<>();
    for (Sensor sensor : sensors){
      sensorInfo.add(ParsingUtils.SensorToSensorInfo(sensor));
    }

    JsonNode json;
    try {
      byte[] jsonBody = objectMapper.writeValueAsBytes(sensorInfo);
      json = SimulatorHttp.sendPostRequest(url, new String(jsonBody));
    } catch (IOException | InterruptedException e) {
      throw new PostRequestException(url);
    }

    return json;
  }
}
