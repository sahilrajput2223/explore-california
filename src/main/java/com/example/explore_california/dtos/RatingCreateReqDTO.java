package com.example.explore_california.dtos;

import lombok.Data;

@Data
public class RatingCreateReqDTO {

    private Integer score;

    private String comment;

    private Integer customerId;
}