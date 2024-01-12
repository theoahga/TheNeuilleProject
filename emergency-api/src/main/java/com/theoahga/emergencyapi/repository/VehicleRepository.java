package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.entity.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, String> {
    Optional<Vehicle> findByImmat(String immat);

}
