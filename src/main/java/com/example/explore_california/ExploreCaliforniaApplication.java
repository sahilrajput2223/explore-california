package com.example.explore_california;

import com.example.explore_california.models.Difficulty;
import com.example.explore_california.models.Region;
import com.example.explore_california.service.TourPackageService;
import com.example.explore_california.service.TourService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ExploreCaliforniaApplication implements CommandLineRunner {

    private final String TOUR_IMPORT_FILE = "ExploreCalifornia.json";

    @Autowired
    TourPackageService tourPackageService;

    @Autowired
    TourService tourService;

    public static void main(String[] args) {
        SpringApplication.run(ExploreCaliforniaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /****** Create Tour Packages ******/
        System.out.println("Tour Packages Count : " + tourPackageService.totalTourPackages());
        createAllTourPackages();
        System.out.println("Tour Packages Count After Creation: " + tourPackageService.totalTourPackages());

        /****** Create Tour Packages ******/
        System.out.println("Tour Count : " + tourService.totalTours());
        createAllTours(TOUR_IMPORT_FILE);
        System.out.println("Tour Count After Creation: " + tourService.totalTours());

        /****** Tour Lookup By Difficulty ******/
        System.out.println("Tour Lookup By Difficulty : " + tourService.lookupByDifficulty(Difficulty.Easy).size());

        /****** Tour Lookup By Package Name ******/
        System.out.println("Tour Lookup By Package : " + tourService.lookupByTourPackageName("Backpack Cal").size());
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

    private void createAllTours(String fileToImportTours) throws IOException {
        ToursFromFile.read(fileToImportTours).forEach(data -> tourService.createTour(
                data.packageName(),
                data.title(),
                data.description(),
                data.blurb(),
                data.price(),
                data.length(),
                data.bullets(),
                data.keywords(),
                Difficulty.valueOf(data.difficulty()),
                Region.findByLabel(data.region())
        ));
    }

    record ToursFromFile(String packageName, String title, String description, String blurb, Integer price, String length,
                         String bullets, String keywords, String difficulty, String region) {
        static List<ToursFromFile> read(String fileToImport) throws IOException {
            return new ObjectMapper().readValue(new File(fileToImport), new TypeReference<List<ToursFromFile>>() {
            });
        }
    }
}