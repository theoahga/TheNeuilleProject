package com.theoahga.emergencyapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.theoahga.emergencyapi.entity.Intervention;
import com.theoahga.emergencyapi.entity.Status;
import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.entity.Vehicle;
import com.theoahga.emergencyapi.service.InterventionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.POST;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController()
@RequestMapping(path = "/intervention")
public class InterventionController {
    private final InterventionService interventionService;

    public InterventionController(InterventionService interventionService) {
        this.interventionService = interventionService;
    }


    @GetMapping(value = "/getAllStarted", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Intervention>> getAllStarted() {
        return ResponseEntity.ok(interventionService.getAllStarted());
    }

    @PostMapping(value = "/deleteAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAll() {
        interventionService.deleteAll();
        return ResponseEntity.ok("All interventions was deleted !");
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Intervention> create(@RequestBody JsonNode jsonNode) {
        Long id = jsonNode.get("id").asLong();
        Date startDate = new Date(Long.parseLong(jsonNode.get("startDate").asText()));
        Date endDate = null;
        Status status = Status.valueOf(jsonNode.get("status").asText());

        List<Unit> units = new ArrayList<>();
        JsonNode unitsNode = jsonNode.get("units");
        for (Iterator<JsonNode> it = unitsNode.elements(); it.hasNext(); ) {
            JsonNode node = it.next();
            units.add(new Unit(node.get("id").asLong()));
        }

        List<Vehicle> vehicles = new ArrayList<>();
        JsonNode vehiclesNode = jsonNode.get("vehicles");
        for (Iterator<JsonNode> it = vehiclesNode.elements(); it.hasNext(); ) {
            JsonNode node = it.next();
            vehicles.add(new Vehicle(node.get("immat").asText()));
        }

        int cid = jsonNode.get("cid").asInt();

        Intervention intervention = new Intervention(cid, startDate, endDate, status, units, vehicles);

        interventionService.create(intervention);

        return ResponseEntity.ok(intervention);
    }
}
