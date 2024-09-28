package com.zomato.zomato_app.config;

import com.zomato.zomato_app.entity.Restaurant;
import com.zomato.zomato_app.repository.RestaurantRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Objects;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVParserBuilder;
import org.springframework.transaction.annotation.Transactional;
import com.opencsv.exceptions.CsvValidationException;

@Component
@Configuration
public class Config implements WebMvcConfigurer {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostConstruct
    @Transactional
    public void loadCsvData() {
        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/Zomato.csv"))));
                CSVReader csvReader = new CSVReaderBuilder(reader)
                        .withCSVParser(new CSVParserBuilder().withSeparator(',').build())
                        .withSkipLines(1)
                        .build()
        ) {
            String[] nextLine;
            while ((nextLine = csvReader.readNext()) != null) {
                try {
                    Restaurant restaurant = new Restaurant();
                    restaurant.setRestaurantId(Long.parseLong(nextLine[0]));
                    restaurant.setRestaurantName(nextLine[1]);
                    restaurant.setCountryCode(Integer.parseInt(nextLine[2]));
                    restaurant.setCity(nextLine[3]);
                    restaurant.setAddress(nextLine[4]);
                    restaurant.setLocality(nextLine[5]);
                    restaurant.setLocalityVerbose(nextLine[6]);
                    restaurant.setLongitude(Double.parseDouble(nextLine[7]));
                    restaurant.setLatitude(Double.parseDouble(nextLine[8]));
                    restaurant.setCuisines(nextLine[9]);
                    restaurant.setAverageCostForTwo(Integer.parseInt(nextLine[10]));
                    restaurant.setCurrency(nextLine[11]);
                    restaurant.setHasTableBooking(nextLine[12]);
                    restaurant.setHasOnlineDelivery(nextLine[13]);
                    restaurant.setIsDelivering(nextLine[14]);
                    restaurant.setSwitchToOrderMenu(nextLine[15]);
                    restaurant.setPriceRange(Integer.parseInt(nextLine[16]));
                    restaurant.setAggregateRating(Double.parseDouble(nextLine[17]));
                    restaurant.setRatingColor(nextLine[18]);
                    restaurant.setRatingText(nextLine[19]);
                    restaurant.setVotes(Integer.parseInt(nextLine[20]));

                    restaurantRepository.save(restaurant);
                } catch (NumberFormatException ex) {
                    System.err.println("Error parsing line: " + String.join(", ", nextLine));
                    ex.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5174", "http://localhost:5173")  
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
