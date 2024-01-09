package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Intervention;
import com.theoahga.emergencyapi.entity.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface InterventionRepository extends CrudRepository<Intervention, Long> {
}
