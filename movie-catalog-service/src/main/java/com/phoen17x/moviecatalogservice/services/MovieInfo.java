package com.phoen17x.moviecatalogservice.services;

import com.phoen17x.moviecatalogservice.models.CatalogItem;
import com.phoen17x.moviecatalogservice.models.Movie;
import com.phoen17x.moviecatalogservice.models.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieInfo {

    private static final String CATALOG_SERVICE = "catalogService";
    private final WebClient.Builder client;

    @Autowired
    public MovieInfo(WebClient.Builder client) {
        this.client = client;
    }

    @CircuitBreaker(name = CATALOG_SERVICE, fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = client.build().get()
                .uri("http://movie-info-service/movies/"+ rating.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    private CatalogItem getFallbackCatalogItem(Rating rating, Exception ex) {
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}
