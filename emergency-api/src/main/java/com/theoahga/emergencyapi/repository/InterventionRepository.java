package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Intervention;
import com.theoahga.emergencyapi.entity.Sensor;
import com.theoahga.emergencyapi.entity.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface InterventionRepository extends CrudRepository<Intervention, Long> {
    public List<Intervention> findAllByEndDateIsNull();

    List<Intervention> findByCid(Long cid);

    Optional<Intervention> findById(Long id);
}
