package com.spark.movie.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movies_images")
public class MovieImage {
    public MovieImage(){

    }

    public MovieImage(String url, String type, String source) {
        this.url = url;
        this.type = type;
        this.source = source;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "movieid", referencedColumnName = "id")
    private Movie movie;

    @Column(columnDefinition = "TEXT")
    private String url;

    @Column(columnDefinition = "TEXT")
    private String type;
    @Column(columnDefinition = "TEXT", name ="imagesource")
    private String source;

    @Column(length = 1)
    private String processed;

    private String localPath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProcessed() {
        return processed;
    }

    public void setProcessed(String processed) {
        this.processed = processed;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
