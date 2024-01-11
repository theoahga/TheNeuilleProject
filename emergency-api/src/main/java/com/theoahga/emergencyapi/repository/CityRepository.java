package com.theoahga.emergencyapi.repository;

import com.theoahga.emergencyapi.entity.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Long> {
  List<City> findBy();
}
