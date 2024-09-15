package com.zomato.zomato_app.controller;

import com.zomato.zomato_app.entity.Restaurant;
import com.zomato.zomato_app.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants/")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantService.findById(id));
    }
    @GetMapping("/by-country")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCountry(@RequestParam int countryCode) {
        return ResponseEntity.ok(restaurantService.findByCountryCode(countryCode));
    }

    @GetMapping
    public ResponseEntity<Page<Restaurant>> getRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Restaurant> restaurantsPage = restaurantService.findAll(pageable);
        return ResponseEntity.ok(restaurantsPage);
    }



    @GetMapping("/by-cost")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCost(
            @RequestParam Double minCost,
            @RequestParam Double maxCost) {
        return ResponseEntity.ok(restaurantService.findByAverageCostForTwo(minCost, maxCost));
    }

    @GetMapping("/by-cuisine")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCuisine(@RequestParam String cuisine) {
        return ResponseEntity.ok(restaurantService.findByCuisines(cuisine));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurantsByName(@RequestParam String name) {
        return ResponseEntity.ok(restaurantService.searchByName(name));
    }

    @GetMapping("/search/city")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCity(@RequestParam String city) {
        return ResponseEntity.ok(restaurantService.findByCity(city));
    }

    @GetMapping("/search/location")
    public ResponseEntity<List<Restaurant>> searchByLocation(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "3") double radiusKm) {
        return ResponseEntity.ok(restaurantService.findByLocation(latitude, longitude, radiusKm));
    }

    // Uncomment when the image search feature is implemented
    // @PostMapping("/search/image")
    // public ResponseEntity<List<Restaurant>> searchByImage(@RequestParam("file") MultipartFile file) throws Exception {
    //     return ResponseEntity.ok(restaurantService.findByImage(file));
    // }
}
