package com.example.explore_california.service;

import com.example.explore_california.models.Difficulty;
import com.example.explore_california.models.Region;
import com.example.explore_california.models.Tour;
import com.example.explore_california.models.TourPackage;
import com.example.explore_california.repository.TourPackageRepository;
import com.example.explore_california.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    TourPackageRepository tourPackageRepository;

    public Tour createTour(String tourPackageName, String title, String description, String blurb, Integer price, String duration, String bullets, String keywords, Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName).orElseThrow(() -> new RuntimeException("Tour Package Not Found for Name: " + tourPackageName));
        return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, difficulty, region, tourPackage));
    }

    public long totalTours() {
        return tourRepository.count();
    }

}