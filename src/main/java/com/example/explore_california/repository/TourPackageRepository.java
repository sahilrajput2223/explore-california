package com.example.explore_california.repository;

import com.example.explore_california.models.TourPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourPackageRepository extends JpaRepository<TourPackage, String> {

    Optional<TourPackage> findByName(String name);
}