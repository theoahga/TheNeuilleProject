package com.theoahga.emergencyapi.controller;

import com.theoahga.emergencyapi.entity.Ville;
import com.theoahga.emergencyapi.repository.VilleRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "/ville")
public class VilleController {

    private final VilleRepository villeRepository;

    public VilleController(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Ville>> getAll() {
        List<Ville> villes = villeRepository.findBy();
        return ResponseEntity.ok(villes);
    }
}
