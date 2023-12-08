package com.theoahga.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.theoahga.exception.GetRequestException;
import com.theoahga.model.Sensor;
import com.theoahga.model.SensorFactory;
import com.theoahga.exception.ZeroException;

import java.io.IOException;
import java.util.List;

public class SensorManagerHttp {

  public static List<Sensor> getSensor() throws GetRequestException, ZeroException {
    String url =
        System.getProperty("ermengency.api.address")
            + ":"
            + System.getProperty("ermengency.api.port")
            + System.getProperty("ermengency.api.endpoint.getsensor");

    JsonNode json;
    try {
      json = SimulatorHttp.sendGetRequest(url);
    } catch (IOException | InterruptedException e) {
      throw new GetRequestException(url);
    }

    return SensorFactory.createSensorsFromJson(json);
  }
}
