package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.Intervention;
import com.theoahga.emergencyapi.repository.InterventionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InterventionService {
    private final InterventionRepository repository;

    public List<Intervention> getAllStarted() {
        return repository.findAllByEndDateIsNull();
    }

    public Intervention create(Intervention intervention) {
        return repository.save(intervention);
    }
}
