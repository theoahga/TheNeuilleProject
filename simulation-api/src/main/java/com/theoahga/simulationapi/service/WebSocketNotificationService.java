package com.theoahga.simulationapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class WebSocketNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyWebSocketEndpoint(String topic, String message) {
        messagingTemplate.convertAndSend("/topic/"+topic, message);
    }
}
