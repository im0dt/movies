package com.spark.movie.tmdb.model;

import java.util.List;

public class THMDBImageResponse {
    private List<THMDBImage> posters;
    private List<THMDBImage> backdrops;

    public List<THMDBImage> getPosters() {
        return posters;
    }

    public void setPosters(List<THMDBImage> posters) {
        this.posters = posters;
    }

    public List<THMDBImage> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<THMDBImage> backdrops) {
        this.backdrops = backdrops;
    }
}
