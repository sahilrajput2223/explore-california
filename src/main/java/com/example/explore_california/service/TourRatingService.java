package com.example.explore_california.service;

import com.example.explore_california.dtos.RatingCreateReqDTO;
import com.example.explore_california.models.Tour;
import com.example.explore_california.models.TourRating;
import com.example.explore_california.repository.TourRatingRepository;
import com.example.explore_california.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<RatingCreateReqDTO> getAllRatingsForTour(int tourId) {
        List<TourRating> tourRatings = tourRatingRepository.findByTourId(verifyTour(tourId).getId());
        return tourRatings.stream().map(RatingCreateReqDTO::new).toList();
    }

    public Map<String, Double> getAverageTourRatings(int tourId) {
        List<TourRating> tourRatings = tourRatingRepository.findByTourId(verifyTour(tourId).getId());
        OptionalDouble averageRating = tourRatings.stream().mapToInt(TourRating::getScore).average();
        return Map.of("average", averageRating.isPresent() ? averageRating.getAsDouble() : 0.0d);
    }
}