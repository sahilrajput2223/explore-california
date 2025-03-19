package com.example.explore_california.service;

import com.example.explore_california.models.TourPackage;
import com.example.explore_california.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourPackageService {

    @Autowired
    TourPackageRepository tourPackageRepository;

    public TourPackage createTourPackage(String code, String name) {
        return tourPackageRepository.findById(code).orElse(tourPackageRepository.save(new TourPackage(code, name)));
    }

    public List<TourPackage> lookupAllTourPackages() {
        return tourPackageRepository.findAll();
    }

    public long totalTourPackages() {
        return tourPackageRepository.count();
    }
}