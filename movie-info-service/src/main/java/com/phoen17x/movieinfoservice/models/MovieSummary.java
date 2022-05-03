package com.phoen17x.movieinfoservice.models;

/**
 * model for movie from imdb-api
 */
public class MovieSummary {

    private String id;
    private String title;
    private String plot;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
