package com.example.explore_california.repository;

import com.example.explore_california.models.Difficulty;
import com.example.explore_california.models.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findByDifficulty(Difficulty difficulty);

    List<Tour> findByTourPackageName(String name);
}