package com.example.explore_california.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tour")
@Entity(name = "tour")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(length = 2000)
    private String blurb;

    @Column
    private Integer price;

    @Column
    private String duration;

    @Column(length = 2000)
    private String bullets;

    @Column
    private String keywords;

    @Column
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @Column
    private Region region;

    @ManyToOne
    @JoinColumn(name = "tour_package_code")
    private TourPackage tourPackage;

    public Tour(String title, String description, String blurb, Integer price, String duration, String bullets, String keywords, Difficulty difficulty, Region region, TourPackage tourPackage) {
        this.title = title;
        this.description = description;
        this.blurb = blurb;
        this.price = price;
        this.duration = duration;
        this.bullets = bullets;
        this.keywords = keywords;
        this.difficulty = difficulty;
        this.region = region;
        this.tourPackage = tourPackage;
    }
}