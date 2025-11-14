package com.travelManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travelManagement.model.TravelPackage;
import com.travelManagement.repository.TravelPackageRepository;

@Service
public class TravelPackageService {

    @Autowired
    private TravelPackageRepository packageRepository;

    public TravelPackage savePackage(TravelPackage travelPackage) {
        return packageRepository.save(travelPackage);
    }

    public List<TravelPackage> getAllPackages() {
        return packageRepository.findAll();
    }

    public TravelPackage getPackageById(Long id) {
        return packageRepository.findById(id).orElse(null);
    }

    public void deletePackage(Long id) {
        packageRepository.deleteById(id);
    }
}
