package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.FireMan;
import com.theoahga.emergencyapi.entity.Unit;
import com.theoahga.emergencyapi.repository.FireManRepository;
import com.theoahga.emergencyapi.repository.UnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FiremanService {
    private final FireManRepository fireManRepository;

    public List<FireMan> getAll() {
        return (List<FireMan>) fireManRepository.findAll();
    }

    public Optional<FireMan> getById(Long id) {
        return fireManRepository.findById(id);
    }
}
