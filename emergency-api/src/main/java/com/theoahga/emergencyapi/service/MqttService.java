package com.theoahga.emergencyapi.service;

import lombok.AllArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MqttService {

    private final SensorService sensorService;

    public void handleMqttSensorStates(Message<?> message) {
        sensorService.registerState(message.getPayload().toString());
    }
}
