package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Intervention;
import com.theoahga.emergencyapi.service.InterventionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
