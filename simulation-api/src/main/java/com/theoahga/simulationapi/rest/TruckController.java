package com.theoahga.simulationapi.rest;

import com.theoahga.simulationapi.service.WebSocketNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/truck")
public class TruckController {
    private final WebSocketNotificationService webSocketNotificationService;

    public TruckController(WebSocketNotificationService webSocketNotificationService){
        this.webSocketNotificationService = webSocketNotificationService;
    }
    @PostMapping(value = "/publishTrucksGps")
    public ResponseEntity<String> publishTrucksGps(@RequestBody String body){
        webSocketNotificationService.notifyWebSocketEndpoint("truck",body);
        return ResponseEntity.ok(body);
    }

}
