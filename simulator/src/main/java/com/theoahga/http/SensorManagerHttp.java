package com.theoahga.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.exception.GetRequestException;
import com.theoahga.exception.PostRequestException;
import com.theoahga.model.Itinary;
import com.theoahga.model.sensor.SensorFactory;
import com.theoahga.exception.ZeroException;
import com.theoahga.model.sensor.api.Sensor;
import com.theoahga.utils.ParsingUtils;
import com.theoahga.model.sensor.api.SensorInfo;

import java.io.IOException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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


  public static JsonNode getActiveIntervention(int cid) throws GetRequestException {
    String url =
            System.getProperty("simulator.api.host")
                    + System.getProperty("simulator.api.endpoint.getActiveIntervention")+"?cid="+cid;

    JsonNode json;
    try {
      json = (JsonNode) SimulatorHttp.sendGetRequest(url);
    } catch (IOException | InterruptedException e) {
      throw new GetRequestException(url);
    }


    return json;
  }

  public static JsonNode setInterventionProgressing(int interventionID) throws PostRequestException {
    String url =
            System.getProperty("simulator.api.host")
                    + System.getProperty("simulator.api.endpoint.setInterventionProgressing")+"?id="+interventionID;

    JsonNode json;
    try {
      json = (JsonNode) SimulatorHttp.sendPostRequest(url, null);
    } catch (IOException | InterruptedException e) {
      throw new PostRequestException(url);
    }


    return json;
  }

  public static Itinary createItinary(JsonNode activeInterventionInPending) throws PostRequestException {
    String url =
            System.getProperty("simulator.api.host")
                    + System.getProperty("simulator.api.endpoint.createItinary");

    Map<String,Map<String,Double>> map = new HashMap<>();

    Map<String, Double> start = new HashMap<>();
    start.put("lon",activeInterventionInPending.get("startLon").asDouble());
    start.put("lat",activeInterventionInPending.get("startLat").asDouble());

    Map<String, Double> end = new HashMap<>();
    end.put("lat",activeInterventionInPending.get("endLat").asDouble());
    end.put("lon",activeInterventionInPending.get("endLon").asDouble());

    map.put("start",start);
    map.put("end",end);


    JsonNode json;
    try {
      json = (JsonNode) SimulatorHttp.sendPostRequest(url, objectMapper.writeValueAsString(map));
    } catch (IOException | InterruptedException e) {
      throw new PostRequestException(url);
    }

    int interventionId = activeInterventionInPending.get("id").asInt();

    List<List<Double>> points = new ArrayList<>();
    for (Iterator<JsonNode> it = json.get("geometry").get("coordinates").elements(); it.hasNext(); ) {
      JsonNode node = it.next();

      List<Double> point = new ArrayList<>();
      point.add(node.get(0).asDouble());
      point.add(node.get(1).asDouble());

      points.add(point);
    }

    LocalDate startDate = LocalDate.parse(activeInterventionInPending.get("date").asText());

    String durationMinute = json.get("duration").asText();
    int durationMinutes = Integer.parseInt(durationMinute) / 10;
    LocalDateTime startDateTime = startDate.atStartOfDay();
    LocalDateTime endDateTime = startDateTime.plusMinutes(durationMinutes);
    LocalDate estimatedEndDate = LocalDate.from(endDateTime);

    Itinary itinary = new Itinary(interventionId,points,startDate,estimatedEndDate);

    return itinary;
  }

  public static JsonNode publishTrucksGps(List<Map<String, Object>> truckGPSPoints) {
    String url =
            System.getProperty("simulator.api.host")
                    + System.getProperty("simulator.api.endpoint.publishTrucksGps");

    JsonNode json;
    try {
      json = (JsonNode) SimulatorHttp.sendPostRequest(url, objectMapper.writeValueAsString(truckGPSPoints));
    } catch (IOException | InterruptedException e) {
      try {
        throw new PostRequestException(url);
      } catch (PostRequestException ex) {
        throw new RuntimeException(ex);
      }
    }


    return json;
  }
}
