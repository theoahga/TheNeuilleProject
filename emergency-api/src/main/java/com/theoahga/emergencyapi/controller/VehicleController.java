package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.entity.Vehicle;
import com.theoahga.emergencyapi.repository.VehicleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/vehicle")
public class VehicleController {
    private final VehicleRepository vehicleRepository;

    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Vehicle>> getAll() {
        List<Vehicle> vehicles = (List<Vehicle>) vehicleRepository.findAll();
        return ResponseEntity.ok(vehicles);
    }
}
