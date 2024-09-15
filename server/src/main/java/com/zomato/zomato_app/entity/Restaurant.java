package com.zomato.zomato_app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(name = "thumb",nullable = true)
    private String thumb;

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "country_code")
    private int countryCode;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "locality")
    private String locality;

    @Column(name = "locality_verbose")
    private String localityVerbose;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "cuisines")
    private String cuisines;

    @Column(name = "average_cost_for_two")
    private int averageCostForTwo;

    @Column(name = "currency")
    private String currency;

    @Column(name = "has_table_booking")
    private String hasTableBooking;

    @Column(name = "has_online_delivery")
    private String hasOnlineDelivery;

    @Column(name = "is_delivering")
    private String isDelivering;

    @Column(name = "switch_to_order_menu")
    private String switchToOrderMenu;

    @Column(name = "price_range")
    private int priceRange;

    @Column(name = "aggregate_rating")
    private double aggregateRating;

    @Column(name = "rating_color")
    private String ratingColor;

    @Column(name = "rating_text")
    private String ratingText;

    @Column(name = "votes")
    private int votes;

    // Default constructor
    public Restaurant() {}

    // Parameterized constructor
    public Restaurant(Long restaurantId, String thumb, String restaurantName, int countryCode, String city, String address, String locality, String localityVerbose, double longitude, double latitude, String cuisines, int averageCostForTwo, String currency, String hasTableBooking, String hasOnlineDelivery, String isDelivering, String switchToOrderMenu, int priceRange, double aggregateRating, String ratingColor, String ratingText, int votes) {
        this.restaurantId = restaurantId;
        this.thumb = thumb;
        this.restaurantName = restaurantName;
        this.countryCode = countryCode;
        this.city = city;
        this.address = address;
        this.locality = locality;
        this.localityVerbose = localityVerbose;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cuisines = cuisines;
        this.averageCostForTwo = averageCostForTwo;
        this.currency = currency;
        this.hasTableBooking = hasTableBooking;
        this.hasOnlineDelivery = hasOnlineDelivery;
        this.isDelivering = isDelivering;
        this.switchToOrderMenu = switchToOrderMenu;
        this.priceRange = priceRange;
        this.aggregateRating = aggregateRating;
        this.ratingColor = ratingColor;
        this.ratingText = ratingText;
        this.votes = votes;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocalityVerbose() {
        return localityVerbose;
    }

    public void setLocalityVerbose(String localityVerbose) {
        this.localityVerbose = localityVerbose;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public int getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(int averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(String hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public String getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(String hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public String getIsDelivering() {
        return isDelivering;
    }

    public void setIsDelivering(String isDelivering) {
        this.isDelivering = isDelivering;
    }

    public String getSwitchToOrderMenu() {
        return switchToOrderMenu;
    }

    public void setSwitchToOrderMenu(String switchToOrderMenu) {
        this.switchToOrderMenu = switchToOrderMenu;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }

    public double getAggregateRating() {
        return aggregateRating;
    }

    public void setAggregateRating(double aggregateRating) {
        this.aggregateRating = aggregateRating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getRatingText() {
        return ratingText;
    }

    public void setRatingText(String ratingText) {
        this.ratingText = ratingText;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
