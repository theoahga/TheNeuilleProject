package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.entity.Ville;
import com.theoahga.emergencyapi.repository.SensorRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/sensor")
public class SensorController {
  private final SensorRepository sensorRepository;

  public SensorController(SensorRepository sensorRepository) {
    this.sensorRepository = sensorRepository;
  }

  @CrossOrigin
  @GetMapping(value = "/getAllById", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAll(@RequestParam Long id) {
    return ResponseEntity.ok(sensorRepository.getAllByIdVille(id));
  }
}
