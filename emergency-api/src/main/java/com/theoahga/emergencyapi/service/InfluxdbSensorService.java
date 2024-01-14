package com.theoahga.emergencyapi.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.influxdb.client.*;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import com.theoahga.emergencyapi.repository.FireTypeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InfluxdbSensorService {
    private String url = System.getProperty("influx.url");

    private String username = System.getProperty("influx.username");

    private char[] password = System.getProperty("influx.password").toCharArray();

    private String bucket = System.getProperty("influx.bucket");

    private String org = System.getProperty("influx.org");

    private InfluxDBClient influxDBClient;
    private final FireTypeRepository fireTypeRepository;

    public InfluxdbSensorService(FireTypeRepository fireTypeRepository) {
        this.fireTypeRepository = fireTypeRepository;


        InfluxDBClientOptions influxDBClientOptions = InfluxDBClientOptions.builder()
                .url(url)
                .authenticate(username, password)
                .bucket(bucket)
                .org(org)
                .build();
        influxDBClient = InfluxDBClientFactory.create(influxDBClientOptions);

        if (!influxDBClient.ping()) {
            System.out.println("Influx Db doesn't pinging");
        }
    }


    public JsonNode writeOneFeature(JsonNode sensorValue) {
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

    public List<Map<String, Object>> readAllActiveStates() {
        /*
        from(bucket:"influx")
|> range(start:0)
|> filter(fn: (r) => r["_measurement"] == "sensor")
|> keep(columns: ["_value","cid", "_time","type"])
|> last(column: "cid")
         */


        String flux = "from(bucket:\"influx\")"
                + "|> range(start:0)"
                + "|> filter(fn: (r) => r[\"_measurement\"] == \"sensor\")"
                + "|> keep(columns: [\"_value\",\"cid\", \"_time\",\"type\"])"
                + "|> last(column: \"cid\")";

        List<Map<String, Object>> result = new ArrayList<>();

        QueryApi queryApi = influxDBClient.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        for (FluxTable fluxTable : tables) {
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord fluxRecord : records) {
                Map<String, Object> map = new HashMap<>();
                map.put("cid", Integer.parseInt((String) fluxRecord.getValueByKey("cid")));
                map.put("intensity", fluxRecord.getValueByKey("_value"));
                map.put("type", fluxRecord.getValueByKey("type"));
                map.put("time", fluxRecord.getValueByKey("_time"));
                result.add(map);
            }
        }

        // Remove Sensors OFF
        List<Integer> idsToRemove = new ArrayList<>();
        for (Map<String, Object> map : result) {
            if (map.get("intensity") == null || (Long) map.get("intensity") == 0) {
                idsToRemove.add((Integer) map.get("cid"));
            }
        }

        if (idsToRemove.size() > 0) {
            List<Map<String, Object>> objectTodelete = new ArrayList<>();
            objectTodelete = result.stream().filter(i -> idsToRemove.contains(i.get("cid"))).collect(Collectors.toList());
            result.removeAll(objectTodelete);
        }

        return result;
    }

    public List<Integer> getEvolutionByCid(long cid) {
        String flux = "from(bucket:\"influx\")"
                + "|> range(start:0)"
                + "|> filter(fn: (r) => r[\"_measurement\"] == \"sensor\")"
                + "|> keep(columns: [\"_value\",\"cid\", \"_time\",\"type\"])"
                + "|> filter(fn: (r) => r[\"cid\"] == \"" + cid + "\")"
                + "|> sort(columns: [\"_time\"])";

        List<Integer> result = new ArrayList<>();

        QueryApi queryApi = influxDBClient.getQueryApi();
        List<FluxTable> tables = queryApi.query(flux);
        for (FluxTable fluxTable : tables) {
            List<FluxRecord> records = fluxTable.getRecords();
            for (FluxRecord fluxRecord : records) {
                fluxRecord.getValueByKey("type");
                result.add(Integer.parseInt(fluxRecord.getValueByKey("_value").toString()));
            }
        }

        return result;
    }
}
