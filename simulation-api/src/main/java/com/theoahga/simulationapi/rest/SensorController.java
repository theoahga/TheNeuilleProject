package com.theoahga.simulationapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.simulationapi.entity.SensorInfo;
import com.theoahga.simulationapi.service.SensorService;
import com.theoahga.simulationapi.service.WebSocketNotificationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(path = "sensor")
public class SensorController {
    private final SensorService sensorService;
    private final RestClient restClient;
    private final WebSocketNotificationService webSocketNotificationService;

    private final ObjectMapper objectMapper;

    public SensorController(SensorService sensorService,WebSocketNotificationService webSocketNotificationService ){
        this.sensorService = sensorService;
        this.webSocketNotificationService = webSocketNotificationService;

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
            webSocketNotificationService.notifyWebSocketEndpoint("sensor",objectMapper.writeValueAsString(updatedSensors));
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


}
