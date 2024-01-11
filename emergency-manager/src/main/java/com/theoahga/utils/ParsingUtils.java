package com.theoahga.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.theoahga.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ParsingUtils {

    public static List<Sensor> createSensorFromJsonNode(JsonNode sensors){
        List<Sensor> sensorList = new ArrayList<>();
        for (Iterator<JsonNode> it = sensors.elements(); it.hasNext(); ) {
            JsonNode node = it.next();

            int cid = node.get("cid").asInt();
            double lat = node.get("lat").asDouble();
            double lon = node.get("lon").asDouble();
            int intensity = node.get("intensity").asInt();
            String type = node.get("type").asText();

           sensorList.add(new Sensor(cid,lat,lon,intensity,type));
        }
        return sensorList;
    }

    public static SensorEvolution createSensorEvolutionFromJsonNode(JsonNode node) {
        List<Integer> values = new ArrayList<>();

        for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
            JsonNode jsonNode = it.next();
            values.add(jsonNode.asInt());
        }

        return new SensorEvolution(values);
    }

    public static List<Intervention> createInterventionsFromJsonNode(JsonNode node) {
        List<Intervention> interventions = new ArrayList<>();

        for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
            JsonNode jsonNode = it.next();

            int id = jsonNode.get("id").asInt();
            LocalDate startDate = LocalDate.parse(jsonNode.get("startDate").asText());
            LocalDate endDate = LocalDate.parse(jsonNode.get("endDate").asText());
            Status status = Status.valueOf(jsonNode.get("id").asText());
            List<Unit> units = createUnitsFromJsonNode(jsonNode.get("units"));
            List<Vehicle> vehicles = createVehiclesFromJsonNode(jsonNode.get("vehicles"));
            int cid = jsonNode.get("cid").asInt();

        }

        return interventions;
    }

    private static List<Unit> createUnitsFromJsonNode(JsonNode node) {
        List<Unit> units = new ArrayList<>();
        for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
            JsonNode jsonNode = it.next();

            int unitId = jsonNode.get("id").asInt();
            boolean isAvaibleUnit = jsonNode.get("available").asBoolean();
            Unit unit = new Unit(unitId, isAvaibleUnit);

            units.add(unit);
        }

        return units;
    }

    public static List<Vehicle> createVehiclesFromJsonNode(JsonNode node) {
        List<Vehicle> vehicles = new ArrayList<>();

        for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
            JsonNode jsonNode = it.next();
            String name = jsonNode.get("name").asText();
            String immat = jsonNode.get("immat").asText();
            String description = jsonNode.get("description").asText();
            int placesNumber = jsonNode.get("placesNumber").asInt();
            String type = jsonNode.get("type").asText();
            Boolean isAvailable = jsonNode.get("isAvailable").asBoolean();

            vehicles.add(new Vehicle(immat,name,description,placesNumber,type, isAvailable));
        }

        return vehicles;
    }
}
