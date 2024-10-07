package com.spark.movie.tmdb.model;

import java.util.List;

public class THMDBFindResponse {
    private List<THMDBMovie> movie_results;

    public List<THMDBMovie> getMovie_results() {
        return movie_results;
    }

    public void setMovie_results(List<THMDBMovie> movie_results) {
        this.movie_results = movie_results;
    }
}
