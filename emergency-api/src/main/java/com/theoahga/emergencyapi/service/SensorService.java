package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.repository.SensorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public List<Sensor> getAll() {
        return sensorRepository.getAllBy();
    }

    public List<Sensor> getAllByIdVille(Long id) {
        return sensorRepository.getAllByCity_Id(id);
    }

    public Optional<Sensor> getById(Long id) {
        return sensorRepository.findById(id);
    }

    public List<Sensor> getAllActive(){
        return sensorRepository.
    }
}
