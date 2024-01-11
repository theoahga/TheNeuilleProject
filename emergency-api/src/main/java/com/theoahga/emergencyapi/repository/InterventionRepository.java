package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Intervention;
import com.theoahga.emergencyapi.entity.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InterventionRepository extends CrudRepository<Intervention, Long> {
    public List<Intervention> findAllByEndDateIsNull();
}
