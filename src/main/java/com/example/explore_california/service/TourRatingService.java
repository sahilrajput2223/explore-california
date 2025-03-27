package com.example.explore_california.service;

import com.example.explore_california.models.Tour;
import com.example.explore_california.models.TourRating;
import com.example.explore_california.repository.TourRatingRepository;
import com.example.explore_california.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TourRatingService {

    @Autowired
    TourRatingRepository tourRatingRepository;

    @Autowired
    TourRepository tourRepository;

    public TourRating createTourRating(int tourId, Integer customerId, Integer score, String comment) {
        return tourRatingRepository.save(new TourRating(verifyTour(tourId), customerId, score, comment));
    }

    private Tour verifyTour(int tourId) {
        return tourRepository.findById(tourId).orElseThrow(() -> new NoSuchElementException("Tour does not exist " + tourId));
    }
}