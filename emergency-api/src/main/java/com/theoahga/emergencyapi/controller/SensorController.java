package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.*;
import com.theoahga.emergencyapi.repository.*;
import com.theoahga.emergencyapi.service.SensorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(path = "/sensor")
public class SensorController {
  private final SensorService sensorService;

  private final FireManRepository fireManRepository;
  private final FireTypeRepository fireTypeRepository;
  private final UnitRepository unitRepository;
  private final StationRepository stationRepository;

  public SensorController(SensorService sensorService, FireManRepository fireManRepository,
                          FireTypeRepository fireTypeRepository, UnitRepository unitRepository,
                          StationRepository stationRepository) {

    this.sensorService = sensorService;
    this.fireManRepository = fireManRepository;
    this.fireTypeRepository = fireTypeRepository;
    this.unitRepository = unitRepository;
    this.stationRepository = stationRepository;
  }

  @GetMapping(value = "/getAllByIdVille", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAllByIdVille(@RequestParam Long id) {
    return ResponseEntity.ok(sensorService.getAllByIdVille(id));
  }

  @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Sensor>> getAll() {
    return ResponseEntity.ok(sensorService.getAll());
  }

  @GetMapping(value = "/getById", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Optional<Sensor>> getById(@RequestParam Long id) {
    return ResponseEntity.ok(sensorService.getById(id));
  }

  @GetMapping(value = "/test")
  public ResponseEntity<String> ok() {
    stationRepository.saveAll(List.of(
            new Station(45.75223304888803, 4.826356465032533, "9 Rue de Condé, 69002 Lyon, France", "Caserne Perrache"),
            new Station(45.76066862832485, 4.835709583359651, "Le bassin central de la place, Place de la République, 69002 Lyon, France", "Caserne République"),
            new Station(45.78353444202694, 4.868465497060774, "12 Rue Enrico Fermi, 69100 Villeurbanne, France", "Caserne Cpe"),
            new Station(45.78351016802518, 4.877747779545341, "3 Avenue Jean Capelle, 69100 Villeurbanne, France", "Caserne Insa")
    ));
    return ResponseEntity.ok("ok");
  }

}
