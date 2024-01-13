package com.theoahga.simulationapi.service;

import lombok.AllArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MqttNotificationService {
    public static final String MQTT_SIMULATION_SENSOR_TOPIC = "simulation/sensor";
    private int qos = 0;
    private String broker = "tcp://localhost:1883";
    private String clientId = "simulator-api-clientid";
    private MemoryPersistence persistence ;
    private MqttClient mqttClient;


    public MqttNotificationService(){
        this.persistence = new MemoryPersistence();

        try {
            mqttClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            mqttClient.connect(connOpts);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }


    public void notifyMqttTopic(String topic, String message){
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(qos);

        try {
            mqttClient.publish(topic, mqttMessage);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

}
