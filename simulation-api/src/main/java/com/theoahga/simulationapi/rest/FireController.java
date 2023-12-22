package com.theoahga.simulationapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoahga.simulationapi.entity.FireInfo;
import com.theoahga.simulationapi.entity.SensorInfo;
import com.theoahga.simulationapi.repository.FireInfoRepository;
import com.theoahga.simulationapi.service.FireService;
import com.theoahga.simulationapi.service.WebSocketNotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "fire")
public class FireController {
    private final WebSocketNotificationService webSocketNotificationService;
    private final FireService fireService;
    private final ObjectMapper objectMapper;

    public FireController(WebSocketNotificationService webSocketNotificationService, FireService fireService){
        this.webSocketNotificationService = webSocketNotificationService;
        this.fireService = fireService;

        this.objectMapper = new ObjectMapper();
    }


    @GetMapping(value = "/getAllStates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FireInfo>> getAllStates(){
        return ResponseEntity.ok(fireService.getAll());
    }

    @GetMapping(value = "/reset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> reset(){
        fireService.resetFires();
        return ResponseEntity.ok("Database fire reset.");
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FireInfo>> update(@RequestBody List<FireInfo> fireInfos) {
        List<FireInfo> updatedFires = fireService.update(fireInfos);

        try {
            webSocketNotificationService.notifyWebSocketEndpoint("fire", new ObjectMapper().writeValueAsString(updatedFires));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok(updatedFires);
    }


}
