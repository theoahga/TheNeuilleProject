package com.theoahga.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.model.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class EmergencyHttp {

  public static Object sendGetRequest(String urlToGet) throws IOException, InterruptedException {
    if (!urlToGet.startsWith("http")) {
      urlToGet = "http://" + urlToGet;
    }

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(urlToGet)).build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    ObjectMapper objectMapper = new ObjectMapper();

    JsonNode jsonNode;


    try {
      return objectMapper.readTree(response.body());
    } catch (JsonProcessingException e) {
      // Nothing to do
    }

    return response.body();

  }

  public static JsonNode sendPostRequest(String urlToPost, Object requestBody) throws IOException, InterruptedException {
    if (!urlToPost.startsWith("http")) {
      urlToPost = "http://" + urlToPost;
    }

    HttpClient client = HttpClient.newHttpClient();

    ObjectMapper objectMapper = new ObjectMapper();
    String requestBodyStr = objectMapper.writeValueAsString(requestBody);

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlToPost))
            .header("Content-Type", "application/json")  // Set the content type if your server expects JSON
            .POST(HttpRequest.BodyPublishers.ofString(requestBodyStr))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


    JsonNode jsonNode = objectMapper.readTree(response.body());

    return jsonNode;
  }

  public static List<Sdis> getSdisDefinition() throws IOException, InterruptedException {
    // TODO :: refactor

    List<Sdis> sdisList = new ArrayList<>();
    String getAllStationUrl = System.getProperty("emergency.api.host") +  System.getProperty("emergency.api.endpoint.getallstation");
    JsonNode res = (JsonNode) sendGetRequest(getAllStationUrl);

    List<Station> stations = new ArrayList<>();
    for (Iterator<JsonNode> it = res.elements(); it.hasNext(); ) {
      JsonNode jsonNode = it.next();

      // STATIONS
      int id = jsonNode.get("id").asInt();
      double lat = jsonNode.get("lat").asDouble();
      double lon = jsonNode.get("lon").asDouble();
      String address = jsonNode.get("address").asText();
      String name = jsonNode.get("name").asText();

      Station station = new Station(id,lat,lon,address,name);
      stations.add(station);

      // SDIS
      JsonNode sdisNode = jsonNode.get("sdis");
      int idSdis = sdisNode.get("id").asInt();
      String nameSdis = sdisNode.get("nom").asText();
      int cityId = sdisNode.get("city").get("id").asInt();

      if(!sdisList.stream().anyMatch(i->i.getId() == idSdis)){
        Sdis sdis = new Sdis(idSdis, nameSdis, cityId);
        sdis.addStations(station);
        sdisList.add(sdis);
      }else {
        Sdis sdis = sdisList.stream().filter(i->i.getId() == idSdis).collect(Collectors.toList()).get(0);
        sdis.addStations(station);
      }
    }

    String getAllUnitUrl = System.getProperty("emergency.api.host") +  System.getProperty("emergency.api.endpoint.getallunit");
    res = (JsonNode) sendGetRequest(getAllUnitUrl);

    List<FireType> types = new ArrayList<>();
    for (Iterator<JsonNode> it = res.elements(); it.hasNext(); ) {
      JsonNode jsonNode = it.next();

      int unitId = jsonNode.get("id").asInt();
      boolean isAvaibleUnit = jsonNode.get("available").asBoolean();
      Unit unit = new Unit(unitId, isAvaibleUnit);

      JsonNode specialitiesNode = jsonNode.get("specialities");

      for (Iterator<JsonNode> ite = specialitiesNode.elements(); ite.hasNext(); ) {
        FireType type = null;
        JsonNode snode = ite.next();
        String speName = snode.get("name").asText();
        int speNumber = snode.get("number").asInt();

        List<FireType> ls = types.stream().filter(i->i.getNumber() == speNumber).collect(Collectors.toList());
        type = ls.size() > 0 ? ls.get(0) : null;
        if(type == null){
          type = new FireType(speName,speNumber);
          types.add(type);
        }

        unit.addSpecility(type);
      }

      int idStation = jsonNode.get("station").get("id").asInt();
      stations.stream().filter(i->i.getId() == idStation).collect(Collectors.toList()).get(0).addUnit(unit);
    }

    String getAllVehicle = System.getProperty("emergency.api.host") +  System.getProperty("emergency.api.endpoint.getallvehicle");
    res = (JsonNode) sendGetRequest(getAllVehicle);

    for (Iterator<JsonNode> it = res.elements(); it.hasNext(); ) {
      JsonNode jsonNode = it.next();
      String name = jsonNode.get("name").asText();
      String immat = jsonNode.get("immat").asText();
      String description = jsonNode.get("description").asText();
      int placesNumber = jsonNode.get("placesNumber").asInt();
      String type = jsonNode.get("type").asText();
      Boolean isAvailable = jsonNode.get("isAvailable").asBoolean();

      Vehicle vehicle = new Vehicle(immat,name,description,placesNumber,type, isAvailable);

      JsonNode firetypes = jsonNode.get("associatedFireTypes");
      for (Iterator<JsonNode> ite = firetypes.elements(); ite.hasNext(); ) {
        JsonNode snode = ite.next();
        int number = snode.get("number").asInt();
        vehicle.addTypes(types.stream().filter(i->i.getNumber() == number).collect(Collectors.toList()).get(0));
      }

      int idStation = jsonNode.get("station").get("id").asInt();
      stations.stream().filter(i->i.getId() == idStation).collect(Collectors.toList()).get(0).addVehicle(vehicle);
    }

    return sdisList;
  }

  public static List<Sensor> getActiveSensors() {
    String url = System.getProperty("emergency.api.host") + System.getProperty("emergency.api.endpoint.getSensor");

    JsonNode result;
    try {
      result = (JsonNode) sendGetRequest(url);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }


  }

  public static SensorEvolution getSensorEvolutionBySensorId(int cid) {
    // TODO
  }

  public static List<Intervention> getStartedInterventions() {
    // TODO
  }
}
