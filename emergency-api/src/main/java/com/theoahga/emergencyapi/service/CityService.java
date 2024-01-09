package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.City;
import com.theoahga.emergencyapi.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> getAll() {
        return cityRepository.findBy();
    }
}
