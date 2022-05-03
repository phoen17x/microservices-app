package com.phoen17x.ratingsdataservice.resources;

import com.phoen17x.ratingsdataservice.models.Rating;
import com.phoen17x.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    /**
     * @param movieId   movie id
     * @return          Rating based on movie name
     */
    @GetMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 5);
    }

    /**
     * @param userId    user id
     * @return          list of user ratings
     */
    @GetMapping("/users/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {

        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;
    }
}
