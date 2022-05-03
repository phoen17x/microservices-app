package com.phoen17x.ratingsdataservice.models;

import java.util.List;

/**
 *  user ratings model
 */
public class UserRating {

    private String userId;
    private List<Rating> ratings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void initData(String userId) {
        this.setUserId(userId);
        this.setRatings(List.of(
                new Rating("tt1375666", 4)
        ));
    }
}
