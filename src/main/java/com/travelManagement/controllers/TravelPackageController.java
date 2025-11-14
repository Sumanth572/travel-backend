package com.travelManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travelManagement.model.TravelPackage;
import com.travelManagement.services.TravelPackageService;

@RestController
@RequestMapping("/api/packages")
public class TravelPackageController {

    @Autowired
    private TravelPackageService packageService;

    @GetMapping
    public List<TravelPackage> getAllPackages() { return packageService.getAllPackages(); }

    @PostMapping
    public TravelPackage savePackage(@RequestBody TravelPackage travelPackage) {
        return packageService.savePackage(travelPackage);
    }

    @DeleteMapping("/delete")
    public void deletePackage(@RequestBody TravelPackage travelPackage) {
        packageService.deletePackage(travelPackage.getId());
    }
    @GetMapping("/ping")
    public String ping() { return "packages pong"; }
}
