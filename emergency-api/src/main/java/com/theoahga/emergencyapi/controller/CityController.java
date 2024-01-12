package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.City;
import com.theoahga.emergencyapi.repository.CityRepository;
import com.theoahga.emergencyapi.service.CityService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/ville")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<City>> getAll() {
        List<City> cities = cityService.getAll();
        return ResponseEntity.ok(cities);
    }
}
