package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Unit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UnitRepository extends CrudRepository<Unit, Long> {
    Optional<Unit> findById(Long id);
}
