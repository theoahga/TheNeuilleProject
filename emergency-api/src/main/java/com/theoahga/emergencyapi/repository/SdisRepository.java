package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Sdis;
import com.theoahga.emergencyapi.entity.Sensor;
import org.springframework.data.repository.CrudRepository;

public interface SdisRepository extends CrudRepository<Sdis, Long> {
}
