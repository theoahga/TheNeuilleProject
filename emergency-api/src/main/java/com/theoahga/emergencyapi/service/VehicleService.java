package com.theoahga.emergencyapi.service;

import com.theoahga.emergencyapi.entity.Vehicle;
import com.theoahga.emergencyapi.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public List<Vehicle> getAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    public Vehicle update(Vehicle vehicle) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findByImmat(vehicle.getImmat());
        if (optionalVehicle.isPresent()) {
            Vehicle storedVehicle = optionalVehicle.get();

            storedVehicle.setName(vehicle.getName());
            storedVehicle.setType(vehicle.getType());
            storedVehicle.setAssociatedFireTypes(vehicle.getAssociatedFireTypes());
            storedVehicle.setPlacesNumber(vehicle.getPlacesNumber());
            storedVehicle.setAvailable(vehicle.isAvailable());

            return vehicleRepository.save(storedVehicle);
        }
        return null;
    }

    public void reset() {
        List<Vehicle> vehicles = getAll();
        vehicles.stream().forEach(i -> i.setAvailable(true));
        vehicleRepository.saveAll(vehicles);
    }
}
