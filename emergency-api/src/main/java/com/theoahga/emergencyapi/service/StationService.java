package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.Station;
import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.entity.Vehicle;
import com.theoahga.emergencyapi.repository.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StationService {
    private final StationRepository stationRepository;

    public List<Station> getAll() {
        return (List<Station>) stationRepository.findAll();
    }
}
