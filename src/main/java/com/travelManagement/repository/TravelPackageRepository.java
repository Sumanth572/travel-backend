package com.travelManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.travelManagement.model.TravelPackage;

public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
}
