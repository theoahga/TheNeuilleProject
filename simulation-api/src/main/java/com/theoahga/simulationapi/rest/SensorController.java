package com.theoahga.simulationapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.simulationapi.entity.SensorInfo;
import com.theoahga.simulationapi.service.MqttNotificationService;
import com.theoahga.simulationapi.service.SensorService;
import com.theoahga.simulationapi.service.WebSocketNotificationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.*;

@RestController
@RequestMapping(path = "sensor")
public class SensorController {
    private final SensorService sensorService;
    private final RestClient restClient;
    private final WebSocketNotificationService webSocketNotificationService;
    private final MqttNotificationService mqttNotificationService;

    private final ObjectMapper objectMapper;

    public SensorController(SensorService sensorService,WebSocketNotificationService webSocketNotificationService, MqttNotificationService mqttNotificationService){
        this.sensorService = sensorService;
        this.webSocketNotificationService = webSocketNotificationService;
        this.mqttNotificationService = mqttNotificationService;

        this.restClient = RestClient.create();
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping(value = "/init", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> init(@RequestBody List<SensorInfo> sensors) {
        List<SensorInfo> savedSensors = sensorService.init(sensors);

        return ResponseEntity.ok(savedSensors.size() + " has been registred.");
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateList(@RequestBody List<SensorInfo> sensors) {
        List<SensorInfo> updatedSensors = sensorService.update(sensors.stream().toList());

        try {
            String sensorsDefinition = objectMapper.writeValueAsString(updatedSensors);
            webSocketNotificationService.notifyWebSocketEndpoint("sensor", sensorsDefinition);
            mqttNotificationService.notifyMqttTopic(MqttNotificationService.MQTT_SIMULATION_SENSOR_TOPIC, adaptSensorsForMqtt(sensorService.getAllStates()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(updatedSensors.size() + " has been updated.");
    }

    @GetMapping(value = "/getAllWithDescriptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllWithDescriptions(){
        String url = System.getProperty("ermengency.api.host") + System.getProperty("ermengency.api.endpoint.getallsensor");
        String result = restClient.get().uri(url).retrieve().body(String.class);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/getDescriptionById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDescriptionById(@RequestParam int id){
        String url = System.getProperty("ermengency.api.host") + System.getProperty("ermengency.api.endpoint.getsensorbyid");
        url+="?id="+id;
        String result = restClient.get().uri(url).retrieve().body(String.class);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/getDescriptionByIdVille", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDescriptionByIdVille(@RequestParam int id){
        String url = System.getProperty("ermengency.api.host") + System.getProperty("ermengency.api.endpoint.getsensorbyidville");
        url+="?id="+id;
        String result = restClient.get().uri(url).retrieve().body(String.class);

        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/getAllStates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SensorInfo>> getAllStates(){
        return ResponseEntity.ok(sensorService.getAllStates());
    }

    @GetMapping(value = "/getStateById", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<SensorInfo>> getStatesById(@RequestParam Long id){
        return ResponseEntity.ok(sensorService.getStateById(id));
    }

    private String adaptSensorsForMqtt(List<SensorInfo> sensors){
        List<Map<String,Object>> list = new ArrayList<>();
        for (SensorInfo sensorInfo: sensors){
            Map<String,Object> map = new HashMap<>();
            map.put("cid",sensorInfo.getCid());
            map.put("i",sensorInfo.getIntensity());
            map.put("t",sensorInfo.getType());
            list.add(map);
        }
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
