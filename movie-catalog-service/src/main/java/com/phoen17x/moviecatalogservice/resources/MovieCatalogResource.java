package com.phoen17x.moviecatalogservice.resources;

import com.phoen17x.moviecatalogservice.models.CatalogItem;
import com.phoen17x.moviecatalogservice.models.UserRating;
import com.phoen17x.moviecatalogservice.services.MovieInfo;
import com.phoen17x.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final MovieInfo movieInfo;
    private final UserRatingInfo userRatingInfo;

    @Autowired
    public MovieCatalogResource(MovieInfo movieInfo,
                                UserRatingInfo userRatingInfo) {
        this.movieInfo = movieInfo;
        this.userRatingInfo = userRatingInfo;
    }

    /**
     * GET user ratings then GET movies based on that data
     *
     * @param userId    user id
     * @return          list of movies that user rated
     */
    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating userRating = userRatingInfo.getUserRating(userId);
        return userRating.getRatings().stream()
                .map(movieInfo::getCatalogItem)
                .collect(Collectors.toList());
    }
}
