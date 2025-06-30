package com.example.explore_california.controller;

import com.example.explore_california.dtos.RatingCreateReqDTO;
import com.example.explore_california.models.TourRating;
import com.example.explore_california.service.TourRatingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {

    @Autowired
    TourRatingService tourRatingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        log.info("POST : /tours/{}/ratings", tourId);
        tourRatingService.createTourRating(tourId, ratingCreateReqDTO.getCustomerId(), ratingCreateReqDTO.getScore(), ratingCreateReqDTO.getComment());
    }

    @PutMapping
    private void updateTourRating(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        log.info("PUT : /tours/{}/ratings", tourId);
        tourRatingService.updateTourRating(tourId, ratingCreateReqDTO);
    }

    @PatchMapping
    private void updateTourRatingWithPatch(@PathVariable(value = "tourId") int tourId, @RequestBody RatingCreateReqDTO ratingCreateReqDTO) {
        log.info("PATCH : /tours/{}/ratings", tourId);
        tourRatingService.updateTourRatingWithPatch(tourId, ratingCreateReqDTO);
    }

    @GetMapping
    private List<RatingCreateReqDTO> getAllRatingsForTour(@PathVariable(value = "tourId") int tourId) {
        log.info("GET : /tours/{}/ratings", tourId);
        return tourRatingService.getAllRatingsForTour(tourId);
    }

    @DeleteMapping("/{customerId}")
    public void deleteTourRating(@PathVariable(value = "tourId") int tourId, @PathVariable(value = "customerId") int customerId) {
        log.info("DELETE : /tours/{}/ratings/{}", tourId, customerId);
        tourRatingService.deleteTourRating(tourId, customerId);
    }

    @GetMapping(value = "/average")
    private Map<String, Double> getAverageTourRatings(@PathVariable(value = "tourId") int tourId) {
        log.info("GET : /tours/{}/ratings/average", tourId);
        return tourRatingService.getAverageTourRatings(tourId);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public void createManyTourRatings(@PathVariable(value = "tourId") int tourId,
                                      @RequestParam(value = "score") int score,
                                      @RequestBody List<Integer> customers){
        log.info("POST : /tours/{}/ratings/batch", tourId);
        tourRatingService.createManyTourRating(tourId, score, customers);
    }
}