package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.*;
import com.theoahga.emergencyapi.repository.*;
import com.theoahga.emergencyapi.service.InfluxdbSensorService;
import com.theoahga.emergencyapi.service.SensorService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/sensor")
public class SensorController {
  private final SensorService sensorService;

  private final FireManRepository fireManRepository;
  private final FireTypeRepository fireTypeRepository;
  private final UnitRepository unitRepository;
  private final StationRepository stationRepository;
  private final InfluxdbSensorService influxdbSensorService;

  public SensorController(SensorService sensorService, FireManRepository fireManRepository,
                          FireTypeRepository fireTypeRepository, UnitRepository unitRepository,
                          StationRepository stationRepository, InfluxdbSensorService influxdbSensorService) {

    this.sensorService = sensorService;
    this.fireManRepository = fireManRepository;
    this.fireTypeRepository = fireTypeRepository;
    this.unitRepository = unitRepository;
    this.stationRepository = stationRepository;
    this.influxdbSensorService = influxdbSensorService;
  }

  @GetMapping(value = "/getAllByIdVille", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAllByIdVille(@RequestParam Long id) {
    return ResponseEntity.ok(sensorService.getAllByIdVille(id));
  }

  @CrossOrigin
  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAll() {
    return ResponseEntity.ok(sensorService.getAll());
  }

  @CrossOrigin
  @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Optional<Sensor>> getById(@RequestParam Long id) {
    return ResponseEntity.ok(sensorService.getById(id));
  }

  @PostMapping(value = "/registerStates", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> registerStates(@RequestBody String sensorsStates) {
    return ResponseEntity.ok(sensorService.registerState(sensorsStates));
  }

  @GetMapping(value = "/getAllActive", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Map<String, Object>>> getAllActive() {
    return ResponseEntity.ok(sensorService.getActiveStates());
  }

  @CrossOrigin
  @GetMapping(value = "/getAllById", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAll(@RequestParam Long id) {
    return ResponseEntity.ok(sensorService.getAllByIdVille(id));
  }
}
