package com.example.explore_california.controller;

import com.example.explore_california.dtos.RatingCreateReqDTO;
import com.example.explore_california.service.TourRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}