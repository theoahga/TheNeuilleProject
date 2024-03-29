package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Sensor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SensorRepository extends CrudRepository<Sensor, Long> {
  List<Sensor> getAllByCity_Id(Long idVille);

  List<Sensor> getAllBy();

  Sensor getByCid(Long cid);
}
