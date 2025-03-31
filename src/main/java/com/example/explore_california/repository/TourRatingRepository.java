package com.example.explore_california.repository;

import com.example.explore_california.models.TourRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends JpaRepository<TourRating, Integer> {

    List<TourRating> findByTourId(int id);

    Optional<TourRating> findByTourIdAndCustomerId(int id, int customerId);
}