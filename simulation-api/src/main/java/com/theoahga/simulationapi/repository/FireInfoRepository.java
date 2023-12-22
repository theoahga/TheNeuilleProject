package com.theoahga.simulationapi.repository;

import com.theoahga.simulationapi.entity.FireInfo;
import com.theoahga.simulationapi.entity.SensorInfo;
import org.springframework.data.repository.CrudRepository;

public interface FireInfoRepository extends CrudRepository<FireInfo, String> {

}
