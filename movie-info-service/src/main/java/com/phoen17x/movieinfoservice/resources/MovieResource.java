package com.phoen17x.movieinfoservice.resources;

import com.phoen17x.movieinfoservice.models.Movie;
import com.phoen17x.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}") //api key to access imdb-api
    private String apiKey;

    private final WebClient.Builder client;

    @Autowired
    public MovieResource(WebClient.Builder client) {
        this.client = client;
    }

    /**
     * GET movie from imdb-api
     * @param movieId   movie id
     * @return          requested movie
     */
    @GetMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {

        MovieSummary movieSummary = client.build().get()
                .uri("https://imdb-api.com/en/API/Title/" + apiKey + "/" + movieId)
                .retrieve()
                .bodyToMono(MovieSummary.class)
                .block();
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getPlot());
    }
}
