package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Vehicle;
import com.theoahga.emergencyapi.service.InterventionService;
import com.theoahga.emergencyapi.service.UnitService;
import com.theoahga.emergencyapi.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(path = "/maintenance")
public class MainenanceController {


    private final InterventionService interventionService;
    private final UnitService unitService;
    private final VehicleService vehicleService;

    public MainenanceController(InterventionService interventionService, UnitService unitService, VehicleService vehicleService) {
        this.interventionService = interventionService;
        this.unitService = unitService;
        this.vehicleService = vehicleService;
    }

    @PostMapping(value = "/reset")
    public ResponseEntity<String> reset() {
        interventionService.reset();
        unitService.reset();
        vehicleService.reset();

        return ResponseEntity.ok("Emergency was reset to its original state !");
    }
}
