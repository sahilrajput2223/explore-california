package com.example.explore_california.dtos;

import com.example.explore_california.models.TourRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingCreateReqDTO {

    private Integer score;

    private String comment;

    private Integer customerId;

    public RatingCreateReqDTO(TourRating tourRating) {
        this.score = tourRating.getScore();
        this.comment = tourRating.getComment();
        this.customerId = tourRating.getCustomerId();
    }
}