package com.theoahga.emergencyapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.InfluxDBClientOptions;

import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.exceptions.InfluxException;
import com.theoahga.emergencyapi.repository.FireTypeRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class InfluxdbSensorService {

    private final String url = System.getProperty("influx.url");
    private final String username = System.getProperty("influx.username");
    private final char[] password = System.getProperty("influx.password").toCharArray();
    private final String bucket = System.getProperty("influx.bucket");
    private InfluxDBClient influxDBClient;
    private final FireTypeRepository fireTypeRepository;

    public InfluxdbSensorService(FireTypeRepository fireTypeRepository) {
        this.fireTypeRepository = fireTypeRepository;


        InfluxDBClientOptions influxDBClientOptions = InfluxDBClientOptions.builder()
                .url(url)
                .authenticate(username, password)
                .bucket(bucket)
                .build();
        influxDBClient = InfluxDBClientFactory.create(influxDBClientOptions);

        if (!influxDBClient.ping()) {
            System.out.println("Influx Db doesn't pinging");
        }
    }


    public JsonNode write(JsonNode sensorValue) {
        int cid = sensorValue.get("cid").asInt();
        int intensity = sensorValue.get("i").asInt();
        int type_id = sensorValue.get("t").asInt();

        Point point = Point.measurement("sensor")
                .addTag("cid", String.valueOf(cid))
                .addField("intensity", intensity)
                .addTag("type", fireTypeRepository.findByNumber(type_id).getName())
                .time(Instant.now(), WritePrecision.MS);

        WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();
        writeApi.writePoint(point);

        return sensorValue;
    }

    public JsonNode read();
}
