package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.entity.Unit;
import org.springframework.data.repository.CrudRepository;

public interface UnitRepository extends CrudRepository<Unit, Long> {
}
