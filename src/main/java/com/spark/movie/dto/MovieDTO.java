package com.spark.movie.dto;

import com.spark.movie.model.MovieImage;
import com.spark.movie.model.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class MovieDTO {
    private int id;
    private String imdbid;
    private String name;
    private String aka;
    private int year;
    private int duration;
    private double rating;
    private int votes;
    private String country;
    private String languages;
    private String genres;
    private String plotoutline;
    private String url;
    private boolean seen;
    private String source;
    private List<MovieImageDTO> backdrops;
    private List<MovieImageDTO> posters;
    private List<MovieImageDTO> videos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPlotoutline() {
        return plotoutline;
    }

    public void setPlotoutline(String plotoutline) {
        this.plotoutline = plotoutline;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<MovieImageDTO> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<MovieImageDTO> backdrops) {
        this.backdrops = backdrops;
    }

    public List<MovieImageDTO> getPosters() {
        return posters;
    }

    public void setPosters(List<MovieImageDTO> posters) {
        this.posters = posters;
    }

    public List<MovieImageDTO> getVideos() {
        return videos;
    }

    public void setVideos(List<MovieImageDTO> videos) {
        this.videos = videos;
    }
}
