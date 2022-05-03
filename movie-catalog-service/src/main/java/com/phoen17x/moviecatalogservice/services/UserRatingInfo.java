package com.phoen17x.moviecatalogservice.services;

import com.phoen17x.moviecatalogservice.models.Rating;
import com.phoen17x.moviecatalogservice.models.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class UserRatingInfo {

    private static final String CATALOG_SERVICE = "catalogService";
    private final WebClient.Builder client;

    @Autowired
    public UserRatingInfo(WebClient.Builder client) {
        this.client = client;
    }

    @CircuitBreaker(name = CATALOG_SERVICE, fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) {
        return client.build().get()
                .uri("http://ratings-data-service/ratingsdata/users/" + userId)
                .retrieve()
                .bodyToMono(UserRating.class)
                .block();
    }

    private UserRating getFallbackUserRating(String userId, Exception ex) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(List.of(new Rating("0", 0)));
        return userRating;
    }
}
