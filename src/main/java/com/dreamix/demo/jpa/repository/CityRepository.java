package com.dreamix.demo.jpa.repository;

import com.dreamix.demo.jpa.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Integer> {

    Optional<City> findByName(String name);

    Page<City> findAll(Pageable pageable);
}
