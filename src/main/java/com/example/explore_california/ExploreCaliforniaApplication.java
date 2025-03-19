package com.example.explore_california;

import com.example.explore_california.service.TourPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExploreCaliforniaApplication implements CommandLineRunner {

    @Autowired
    TourPackageService tourPackageService;

    public static void main(String[] args) {
        SpringApplication.run(ExploreCaliforniaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Tour Packages Count : " + tourPackageService.totalTourPackages());
        createAllTourPackages();
        System.out.println("Tour Packages Count After Creation: " + tourPackageService.totalTourPackages());
    }

    private void createAllTourPackages() {
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");
    }
}