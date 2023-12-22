package com.theoahga.simulationapi.service;

import com.theoahga.simulationapi.entity.FireInfo;
import com.theoahga.simulationapi.repository.FireInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FireService {
    private final FireInfoRepository fireInfoRepository;

    public List<FireInfo> getAll() {
        return (List<FireInfo>) fireInfoRepository.findAll();
    }

    public List<FireInfo> update(List<FireInfo> fireInfos) {
        List<FireInfo> fireUpdated = new ArrayList<>();
        for(FireInfo fireInfo : fireInfos){
            Optional<FireInfo> optionalfireInfo = fireInfoRepository.findById(fireInfo.getId());
            if (optionalfireInfo.isPresent()){
                FireInfo fireInfo1 = optionalfireInfo.get();
                fireInfo1.setLon(fireInfo1.getLon());
                fireInfo1.setLat(fireInfo1.getLat());
                fireInfo1.setRadius(fireInfo1.getRadius());

                fireInfoRepository.save(fireInfo1);
                fireUpdated.add(fireInfo1);
            }else{
                fireInfoRepository.save(fireInfo);
            }
        }
        return fireUpdated;
    }

    public void resetFires() {
        fireInfoRepository.deleteAll();
    }
}
