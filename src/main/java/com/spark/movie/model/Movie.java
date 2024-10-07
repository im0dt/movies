package com.spark.movie.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String imdbid;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String aka;
    private int year;
    private int duration;
    private double rating;
    private int votes;
    @Column(columnDefinition = "TEXT")
    private String country;
    @Column(columnDefinition = "TEXT")
    private String languages;
    @Column(columnDefinition = "TEXT")
    private String genres;
    @Column(columnDefinition = "TEXT")
    private String plotoutline;
    @Column(columnDefinition = "TEXT")
    private String url;
    private boolean seen;
    private String source;
    @Transient
    private List<MovieImage> backdrops;
    @Transient
    private List<MovieImage> posters;
    @Transient
    private List<Video> videos;


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

    public List<MovieImage> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<MovieImage> backdrops) {
        this.backdrops = backdrops;
    }

    public List<MovieImage> getPosters() {
        return posters;
    }

    public void setPosters(List<MovieImage> posters) {
        this.posters = posters;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
