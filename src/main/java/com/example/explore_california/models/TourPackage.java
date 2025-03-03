package com.example.explore_california.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name="tour_package")
@Entity(name="tour_package")
public class TourPackage {

    @Id
    private String code;

    @Column
    private String name;
}