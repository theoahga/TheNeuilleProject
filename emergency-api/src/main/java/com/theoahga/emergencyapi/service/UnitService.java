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


}
