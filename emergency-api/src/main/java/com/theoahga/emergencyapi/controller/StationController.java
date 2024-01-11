package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Sdis;
import com.theoahga.emergencyapi.entity.Station;
import com.theoahga.emergencyapi.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/station")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Station>> getAll() {
        List<Station> stationList = stationService.getAll();
        return ResponseEntity.ok(stationList);
    }
}
