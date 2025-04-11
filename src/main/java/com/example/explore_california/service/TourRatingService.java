package com.example.explore_california.service;

import com.example.explore_california.dtos.RatingCreateReqDTO;
import com.example.explore_california.models.Tour;
import com.example.explore_california.models.TourRating;
import com.example.explore_california.repository.TourRatingRepository;
import com.example.explore_california.repository.TourRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TourRatingService {

    @Autowired
    TourRatingRepository tourRatingRepository;

    @Autowired
    TourRepository tourRepository;

    public TourRating createTourRating(int tourId, Integer customerId, Integer score, String comment) {
        return tourRatingRepository.save(new TourRating(verifyTour(tourId), customerId, score, comment));
    }

    public RatingCreateReqDTO updateTourRating(int tourId, RatingCreateReqDTO ratingCreateReqDTO){
        TourRating tourRating = verifyTourRating(tourId, ratingCreateReqDTO.getCustomerId());
        tourRating.setComment(ratingCreateReqDTO.getComment());
        tourRating.setScore(ratingCreateReqDTO.getScore());
        tourRating = tourRatingRepository.save(tourRating);
        return new RatingCreateReqDTO(tourRating);
    }

    public void deleteTourRating(int tourId, int customerId){
        TourRating tourRating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(tourRating);
    }

    public RatingCreateReqDTO updateTourRatingWithPatch(int tourRatingId, RatingCreateReqDTO ratingCreateReqDTO) {
        return new RatingCreateReqDTO(updateSome(tourRatingId, ratingCreateReqDTO.getCustomerId(), Optional.ofNullable(ratingCreateReqDTO.getScore()), Optional.ofNullable(ratingCreateReqDTO.getComment())));
    }

    public TourRating updateSome(int tourId, Integer customerId, Optional<Integer> score, Optional<String> comment) {
        TourRating rating = verifyTourRating(tourId, customerId);
        score.ifPresent(rating::setScore);
        comment.ifPresent(rating::setComment);
        return tourRatingRepository.save(rating);
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

    public void createManyTourRating(int tourId, int score, List<Integer> customers) {
        Tour tour = verifyTour(tourId);
        for (Integer customer : customers){
            if(tourRatingRepository.findByTourIdAndCustomerId(tourId, customer).isPresent()){
                throw new ConstraintViolationException("Unable to create duplicate ratings", null);
            }
            tourRatingRepository.save(new TourRating(tour, customer, score, null));
        }
    }
}