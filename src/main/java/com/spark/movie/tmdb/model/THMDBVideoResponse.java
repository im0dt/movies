package com.spark.movie.tmdb.model;

import java.util.List;

public class THMDBVideoResponse {
    List<THMDBVideo> results;

    public List<THMDBVideo> getResults() {
        return results;
    }

    public void setResults(List<THMDBVideo> results) {
        this.results = results;
    }
}
