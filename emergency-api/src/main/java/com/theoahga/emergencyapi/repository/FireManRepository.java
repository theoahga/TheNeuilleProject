package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.FireMan;
import com.theoahga.emergencyapi.entity.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FireManRepository extends CrudRepository<FireMan, Long> {
}
