package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.entity.Vehicle;
import com.theoahga.emergencyapi.repository.VehicleRepository;
import com.theoahga.emergencyapi.service.VehicleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Vehicle>> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> update(@RequestBody Vehicle vehicle) {
        return ResponseEntity.ok(vehicleService.update(vehicle));
    }
}
