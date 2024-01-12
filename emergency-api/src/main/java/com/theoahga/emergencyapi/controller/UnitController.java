package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Station;
import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.service.UnitService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> update(@RequestBody Unit unit) {
        return ResponseEntity.ok(unitService.update(unit));
    }
}
