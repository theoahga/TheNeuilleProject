package com.theoahga.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.theoahga.model.Sensor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

            Sensor sensor = new Sensor(cid,lat,lon,intensity,type);

        }
    }
}
