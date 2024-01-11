package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.FireType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireTypeRepository extends JpaRepository<FireType, String> {
    FireType findByNumber(int number);
}