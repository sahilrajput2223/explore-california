package com.example.explore_california.controller;

import com.example.explore_california.dtos.RatingCreateReqDTO;
import com.example.explore_california.models.TourRating;
import com.example.explore_california.service.TourRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

    @Autowired
    TourRatingService tourRatingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        tourRatingService.createTourRating(tourId, ratingCreateReqDTO.getCustomerId(), ratingCreateReqDTO.getScore(), ratingCreateReqDTO.getComment());
    }

    @PutMapping
    private void updateTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        tourRatingService.updateTourRating(tourId, ratingCreateReqDTO);
    }

    @PatchMapping
    private void updateTourRatingWithPatch(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        tourRatingService.updateTourRatingWithPatch(tourId, ratingCreateReqDTO);
    }

    @GetMapping
    private List<RatingCreateReqDTO> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
        return tourRatingService.getAllRatingsForTour(tourId);
    }

    @GetMapping(value = "/average")
    private Map<String, Double> getAverageTourRatings(@PathVariable(value = "tourId") int tourId) {
        return tourRatingService.getAverageTourRatings(tourId);
    }
}