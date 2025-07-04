package com.example.explore_california.repository;

import com.example.explore_california.models.TourPackage;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(path = "packages", collectionResourceRel = "packages")
@Tag(name = "Tour Package", description = "Tour Package APIs")
public interface TourPackageRepository extends JpaRepository<TourPackage, String> {

    @RestResource(path = "names")
    Optional<TourPackage> findByName(String name);
}