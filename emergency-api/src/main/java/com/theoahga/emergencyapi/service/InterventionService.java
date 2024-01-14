package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.Intervention;
import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.entity.Status;
import com.theoahga.emergencyapi.entity.Vehicle;
import com.theoahga.emergencyapi.repository.InterventionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InterventionService {
    private final InterventionRepository repository;
    private final SensorService sensorService;

    public List<Intervention> getAllStarted() {
        return repository.findAllByEndDateIsNull();
    }

    public Intervention create(Intervention intervention) {
        return repository.save(intervention);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void reset() {
        deleteAll();
    }

    public Object getActiveInterventionInPending(Long cid) {
        List<Intervention> interventions = repository.findByCid(cid);
        interventions = interventions.stream().filter(i -> i.getEndDate() == null).collect(Collectors.toList());
        if (interventions.size() > 0) {
            Intervention intervention = interventions.get(0);
            Map<String, Object> map = new HashMap<>();
            map.put("id", intervention.getId());
            Sensor sensor = sensorService.getByCid(Long.valueOf(intervention.getCid()));
            map.put("endLon", sensor.getLon());
            map.put("endLat", sensor.getLat());
            map.put("date", intervention.getStartDate());
            map.put("status", intervention.getStatus());

            Vehicle vehicle = intervention.getVehicles().get(0);
            map.put("startLon", vehicle.getStation().getLon());
            map.put("startLat", vehicle.getStation().getLat());
            return map;
        }
        return null;
    }

    public Intervention setInterventionProgressing(Long id) {
        Optional<Intervention> intervention = repository.findById(id);
        if (intervention.isPresent()) {
            Intervention inter = intervention.get();
            inter.setStatus(Status.PROGRESSING);
            return repository.save(inter);
        }

        return null;
    }
}
