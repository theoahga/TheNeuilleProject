package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.entity.Ville;
import com.theoahga.emergencyapi.repository.SensorRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/sensor")
public class SensorController {
  private final SensorRepository sensorRepository;

  public SensorController(SensorRepository sensorRepository) {
    this.sensorRepository = sensorRepository;
  }

  @GetMapping(value = "/getAllByIdVille", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAll(@RequestParam Long id) {
    return ResponseEntity.ok(sensorRepository.getAllByIdVille(id));
  }

  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAll() {
    return ResponseEntity.ok(sensorRepository.getAllBy());
  }

  @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Optional<Sensor>> getById(@RequestParam Long id) {
    return ResponseEntity.ok(sensorRepository.findById(id));
  }
}
