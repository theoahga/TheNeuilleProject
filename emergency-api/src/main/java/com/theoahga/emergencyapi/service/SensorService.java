package com.theoahga.emergencyapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.repository.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final InfluxdbSensorService influxdbSensorService;

    public List<Sensor> getAll() {
        return sensorRepository.getAllBy();
    }

    public List<Sensor> getAllByIdVille(Long id) {
        return sensorRepository.getAllByCity_Id(id);
    }

    public Optional<Sensor> getById(Long id) {
        return sensorRepository.findById(id);
    }

    public String registerState(String sensorStateStates) {
        JsonNode node;

        try {
            node = new ObjectMapper().readTree(sensorStateStates);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        int count = 0;
        for (Iterator<JsonNode> it = node.elements(); it.hasNext(); ) {
            JsonNode jsonNode = it.next();
            influxdbSensorService.writeOneFeature(jsonNode);
            count++;
        }

        return count + " was being registred !";
    }

    public List<Map<String, Object>> getActiveStates() {
        List<Map<String, Object>> sensorsActives = influxdbSensorService.readAllActiveStates();
        sensorsActives.stream().forEach(i -> {
            populateLatLonAndCityid(i);
        });
        return sensorsActives;
    }

    public void populateLatLonAndCityid(Map<String, Object> map) {
        Sensor sensor = getByCid(Long.parseLong(String.valueOf(map.get("cid"))));
        map.put("lat", sensor.getLat());
        map.put("lon", sensor.getLon());
        map.put("cityId", sensor.getCity().getId());
    }

    public Sensor getByCid(Long cid) {
        return sensorRepository.getByCid(cid);
    }


}
