package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Sdis;
import com.theoahga.emergencyapi.repository.SdisRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/sdis")
public class SdisController {
    private final SdisRepository sdisRepository;

    public SdisController(SdisRepository sdisRepository) {
        this.sdisRepository = sdisRepository;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Sdis>> getAll() {
        List<Sdis> sdisList = (List<Sdis>) sdisRepository.findAll();
        return ResponseEntity.ok(sdisList);
    }
}
