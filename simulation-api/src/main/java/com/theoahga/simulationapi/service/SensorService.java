package com.theoahga.simulationapi.service;

import com.theoahga.simulationapi.repository.SensorInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.theoahga.simulationapi.entity.SensorInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SensorService {
    private final SensorInfoRepository sensorInfoRepository;

    public List<SensorInfo> init(List<SensorInfo> sensors){
        sensorInfoRepository.deleteAll();

        sensorInfoRepository.saveAll(sensors);

        return (List<SensorInfo>) sensorInfoRepository.findAll();
    }

    public List<SensorInfo> update(List<SensorInfo> sensors){
        List<SensorInfo> updatedSensor = new ArrayList<>();
        for (SensorInfo sensorInfo : sensors){
            Optional<SensorInfo> optional = sensorInfoRepository.findById(sensorInfo.getCid());
            if(optional.isPresent()){
                SensorInfo sensorJpa = optional.get();

                sensorJpa.setIntensity(sensorInfo.getIntensity());
                sensorJpa.setLat(sensorInfo.getLat());
                sensorJpa.setLon(sensorInfo.getLon());
                sensorJpa.setType(sensorInfo.getType());

                sensorInfoRepository.save(sensorJpa);
                updatedSensor.add(sensorJpa);
            }
        }
        return updatedSensor;
    }

    public List<SensorInfo> getAllStates() {
        return (List<SensorInfo>) sensorInfoRepository.findAll();
    }

    public Optional<SensorInfo> getStateById(long id) {
        return sensorInfoRepository.findById(id);
    }
}
