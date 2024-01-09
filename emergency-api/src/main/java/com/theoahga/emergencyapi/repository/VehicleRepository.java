package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {
}
