package com.example.explore_california.controller;

import com.example.explore_california.dtos.RatingCreateReqDTO;
import com.example.explore_california.service.TourRatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
@Tag(name = "Tour Rating APIs", description = "Tour Rating APIs to Manage Rating By Tour and Customers")
public class TourRatingController {

    @Autowired
    TourRatingService tourRatingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Add Tour Rating")
    private void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        log.info("POST : /tours/{}/ratings", tourId);
        tourRatingService.createTourRating(tourId, ratingCreateReqDTO.getCustomerId(), ratingCreateReqDTO.getScore(), ratingCreateReqDTO.getComment());
    }

    @PutMapping
    @Operation(description = "Edit Tour Rating")
    private void updateTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        log.info("PUT : /tours/{}/ratings", tourId);
        tourRatingService.updateTourRating(tourId, ratingCreateReqDTO);
    }

    @PatchMapping
    @Operation(description = "Partial Tour Rating")
    private void updateTourRatingWithPatch(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        log.info("PATCH : /tours/{}/ratings", tourId);
        tourRatingService.updateTourRatingWithPatch(tourId, ratingCreateReqDTO);
    }

    @GetMapping
    @Operation(description = "Get All Ratings By Tour")
    private List<RatingCreateReqDTO> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
        log.info("GET : /tours/{}/ratings", tourId);
        return tourRatingService.getAllRatingsForTour(tourId);
    }

    @DeleteMapping("/{customerId}")
    @Operation(description = "Delete Tour Rating by Customer Id")
    public void deleteTourRating(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId) {
        log.info("DELETE : /tours/{}/ratings/{}", tourId, customerId);
        tourRatingService.deleteTourRating(tourId, customerId);
    }

    @GetMapping(value = "/average")
    @Operation(description = "Get Average Tour Rating")
    private Map<String, Double> getAverageTourRatings(@PathVariable(value = "tourId") int tourId) {
        log.info("GET : /tours/{}/ratings/average", tourId);
        return tourRatingService.getAverageTourRatings(tourId);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Give Tour Rating in Batch")
    public void createManyTourRatings(@PathVariable(value = "tourId") int tourId,
                                      @RequestParam(value = "score") int score,
                                      @RequestBody List<Integer> customers){
        log.info("POST : /tours/{}/ratings/batch", tourId);
        tourRatingService.createManyTourRating(tourId, score, customers);
    }
}