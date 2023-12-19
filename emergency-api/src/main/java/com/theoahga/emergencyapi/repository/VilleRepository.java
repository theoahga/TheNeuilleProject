package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.Ville;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VilleRepository extends CrudRepository<Ville, Long> {
  List<Ville> findBy();
}
