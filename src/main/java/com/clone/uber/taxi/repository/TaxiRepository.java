package com.clone.uber.taxi.repository;

import com.clone.uber.taxi.entity.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepository extends JpaRepository<Taxi, Long> {
}
