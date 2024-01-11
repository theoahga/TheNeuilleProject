package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Station;
import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/unit")
public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Unit>> getAll() {
        List<Unit> units = unitService.getAll();
        return ResponseEntity.ok(units);
    }
}
