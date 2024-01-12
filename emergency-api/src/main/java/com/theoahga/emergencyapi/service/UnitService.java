package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.FireMan;
import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UnitService {
    private final UnitRepository unitRepository;

    public List<Unit> getAll() {
        return (List<Unit>) unitRepository.findAll();
    }


    public Unit update(Unit unit) {
        Optional<Unit> optionalUnit = unitRepository.findById(unit.getId());
        if (optionalUnit.isPresent()) {
            Unit storedUnit = optionalUnit.get();

            storedUnit.setAvailable(unit.getAvailable());
            storedUnit.setSpecialities(unit.getSpecialities());

            return unitRepository.save(storedUnit);
        }
        return null;
    }

    public void reset() {
        List<Unit> units = getAll();
        units.stream().forEach(i -> i.setAvailable(Boolean.TRUE));
        unitRepository.saveAll(units);
    }
}
