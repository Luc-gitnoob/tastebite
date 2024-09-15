package com.zomato.zomato_app.service;

import com.zomato.zomato_app.entity.Restaurant;
import com.zomato.zomato_app.exception.RestaurantNotFoundException;
import com.zomato.zomato_app.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant findById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public Page<Restaurant> findAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public List<Restaurant> findByCountryCode(int countryCode) {
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getCountryCode() == countryCode)
                .collect(Collectors.toList());
    }

    public List<Restaurant> findByAverageCostForTwo(Double minCost, Double maxCost) {
        double defaultMinCost = 0.0;
        double defaultMaxCost = Double.MAX_VALUE;

        double min = (minCost != null) ? minCost : defaultMinCost;
        double max = (maxCost != null) ? maxCost : defaultMaxCost;

        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getAverageCostForTwo() >= min && restaurant.getAverageCostForTwo() <= max)
                .collect(Collectors.toList());
    }

    public List<Restaurant> findByCuisines(String cuisine) {
        String normalizedCuisine = cuisine.trim().toLowerCase();
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> {
                    String normalizedRestaurantCuisines = restaurant.getCuisines().toLowerCase().replaceAll("\\s+", "");
                    String[] cuisinesArray = normalizedRestaurantCuisines.split(",");
                    for (String c : cuisinesArray) {
                        if (c.equals(normalizedCuisine)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public List<Restaurant> searchByName(String name) {
        String normalizedSearchName = name.trim().toLowerCase();
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getRestaurantName().toLowerCase().contains(normalizedSearchName))
                .collect(Collectors.toList());
    }

    public List<Restaurant> findByCity(String city) {
        String normalizedSearchCity = city.trim().toLowerCase();
        return restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getCity().toLowerCase().contains(normalizedSearchCity))
                .collect(Collectors.toList());
    }

    public List<Restaurant> findByLocation(double latitude, double longitude, double radiusKm) {
        final double EARTH_RADIUS_KM = 6371.0;
        List<Restaurant> allRestaurants = restaurantRepository.findAll();

        return allRestaurants.stream()
                .filter(restaurant -> {
                    double latDistance = toRadians(restaurant.getLatitude() - latitude);
                    double lonDistance = toRadians(restaurant.getLongitude() - longitude);
                    double a = sin(latDistance / 2) * sin(latDistance / 2)
                            + cos(toRadians(latitude)) * cos(toRadians(restaurant.getLatitude()))
                            * sin(lonDistance / 2) * sin(lonDistance / 2);
                    double c = 2 * atan2(sqrt(a), sqrt(1 - a));
                    double distance = EARTH_RADIUS_KM * c;

                    return distance <= radiusKm;
                })
                .collect(Collectors.toList());
    }
}
