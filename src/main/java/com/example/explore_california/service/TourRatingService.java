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

    public RatingCreateReqDTO updateTourRating(int tourRatingId, RatingCreateReqDTO ratingCreateReqDTO){
        TourRating tourRating = verifyTourRating(tourRatingId, ratingCreateReqDTO.getCustomerId());
        tourRating.setComment(ratingCreateReqDTO.getComment());
        tourRating.setScore(ratingCreateReqDTO.getScore());
        tourRating = tourRatingRepository.save(tourRating);
        return new RatingCreateReqDTO(tourRating);
    }

    private Tour verifyTour(int tourId) {
        return tourRepository.findById(tourId).orElseThrow(() -> new NoSuchElementException("Tour does not exist " + tourId));
    }

    private TourRating verifyTourRating(int tourRatingId, int customerId) {
        return tourRatingRepository.findByTourIdAndCustomerId(tourRatingId, customerId).orElseThrow(() -> new NoSuchElementException("Tour Rating does not exist for given tour and customer id"));
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