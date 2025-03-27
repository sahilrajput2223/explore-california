package com.example.explore_california.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tour_rating")
@Table(name = "tour_rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourRating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    private Tour tour;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "comment")
    private String comment;

    public TourRating(Tour tour, Integer customerId, Integer score, String comment) {
        this.tour = tour;
        this.customerId = customerId;
        this.score = score;
        this.comment = comment;
    }
}