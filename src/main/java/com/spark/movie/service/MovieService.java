package com.spark.movie.service;

import com.spark.movie.model.*;
import com.spark.movie.pics.PicsImage;
import com.spark.movie.repository.MovieImageRepository;
import com.spark.movie.repository.MovieRepository;
import com.spark.movie.repository.YearRangeRepository;
import com.spark.movie.tmdb.model.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final YearRangeRepository yearRangeRepository;
    private final MovieImageRepository movieImageRepository;
    @Autowired
    private final THMDBService thmdbService;
    @Autowired
    private final PicsService picsService;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    public MovieService(MovieRepository movieRepository,
                        MovieImageRepository movieImageRepository,
                        YearRangeRepository yearRangeRepository,
                        THMDBService thmdbService,
                        PicsService picsService,
                        EntityManager entityManager) {
        this.movieRepository = movieRepository;
        this.movieImageRepository = movieImageRepository;
        this.yearRangeRepository = yearRangeRepository;
        this.thmdbService = thmdbService;
        this.picsService = picsService;
        this.entityManager = entityManager;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        for (Movie movie : movies){
            movie.setPosters(getMovieImages(movie.getId(), "poster").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
            movie.setBackdrops(getMovieImages(movie.getId(), "backdrop").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
            movie.setVideos(getMovieImages(movie.getId(), "movie").stream().map(x -> new Video(x.getSource(), x.getUrl(), x.getLocalPath())).collect(Collectors.toList()));
        }
        return movies;
    }

    public Optional<Movie> getMovieById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isEmpty())
            return movie;
        movie.get().setPosters(getMovieImages(id, "poster").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
        movie.get().setBackdrops(getMovieImages(id, "backdrop").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
        movie.get().setVideos(getMovieImages(id, "movie").stream().map(x -> new Video(x.getSource(), x.getUrl(), x.getLocalPath())).collect(Collectors.toList()));
        return movie;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void updateMovie(Movie movie){
            movieRepository.save(movie);
    }

    public void createMovieImages(List<MovieImage> movieImages) {
        movieImageRepository.saveAllAndFlush(movieImages);
    }

    public MovieImage createMovieImage(MovieImage movieImage) {
        return movieImageRepository.saveAndFlush(movieImage);
    }
    public void deleteMovie(int id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }
    public List<Movie> getAllMoviesByCategoryId(int categoryId, int count) {
        List<Movie> movies = movieRepository.findByCategory(categoryId, PageRequest.of(0,count));
        for (Movie movie : movies){
            movie.setPosters(getMovieImages(movie.getId(), "poster").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
            movie.setBackdrops(getMovieImages(movie.getId(), "backdrop").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
        }
        return movies;
    }

    public List<Movie> getAllMoviesByRangeId(int rangeId, int count) {
        YearRange range = yearRangeRepository.findById(rangeId).get();
        List<Movie> movies = movieRepository.findByRange(range.getFrom(), range.getTo(),PageRequest.of(0,count));
        for (Movie movie : movies){
            movie.setPosters(getMovieImages(movie.getId(), "poster").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource()))   .collect(Collectors.toList()));
            movie.setBackdrops(getMovieImages(movie.getId(), "backdrop").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
        }
        return movies;
    }

    public List<Movie> getAllMoviesByLanguage(String language, int count) {
        List<Movie> movies = movieRepository.findByLanguage(language,PageRequest.of(0,count));
        for (Movie movie : movies){
            movie.setPosters(getMovieImages(movie.getId(), "poster").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource()))   .collect(Collectors.toList()));
            movie.setBackdrops(getMovieImages(movie.getId(), "backdrop").stream().map(x -> new MovieImage(x.getUrl(), x.getType(), x.getSource())).collect(Collectors.toList()));
        }
        return movies;
    }

    public Movie getMovieByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }
    public List<MovieImage> getMovieImages(int movieId, String type) {
        return movieImageRepository.listMovieImages(movieId, type);
    }

    @Transactional
    public List<MovieImage> extractMovieImages(int id) {
        List<MovieImage> result = new ArrayList<>();
        Movie movie = movieRepository.findById(id).get();
        THMDBImageResponse res = null;
        if (movie.getSource().equals("IMDB")) {
            res = thmdbService.extractMovieImage(movie.getImdbid());
            for (THMDBImage thmdbImage : res.getBackdrops()) {
                if (movieImageRepository.findByUrl(movie.getId(), "backdrop", thmdbImage.getFile_path()) == null) {
                    MovieImage mi = new MovieImage();
                    mi.setMovie(movie);
                    mi.setType("backdrop");
                    mi.setSource("thmoviedb");
                    mi.setUrl(thmdbImage.getFile_path());
                    result.add(mi);
                }
            }
            for (THMDBImage thmdbImage : res.getPosters()) {
                if (movieImageRepository.findByUrl(movie.getId(), "poster", thmdbImage.getFile_path()) == null) {
                    MovieImage mi = new MovieImage();
                    mi.setMovie(movie);
                    mi.setType("poster");
                    mi.setSource("thmoviedb");
                    mi.setUrl(thmdbImage.getFile_path());
                    result.add(mi);
                }
            }
            THMDBVideoResponse res1 = thmdbService.extractMovieVideo(movie.getImdbid());
            for (THMDBVideo thmdbVideo
                    : res1.getResults()) {
                if (movieImageRepository.findByUrl(movie.getId(), "poster", thmdbVideo.getKey()) == null) {
                    MovieImage mi = new MovieImage();
                    mi.setMovie(movie);
                    mi.setType("movie");
                    mi.setSource("thmoviedb");
                    mi.setUrl(thmdbVideo.getKey());
                    result.add(mi);
                }
            }
            if (movie.getUrl() != null) {
                PicsImage[] picsImage = picsService.extractAZImages(movie.getUrl());
                if (picsImage.length > 0) {
                    for (PicsImage pi : picsImage) {
                        if (movieImageRepository.findByUrl(movie.getId(), "backdrop", pi.getUrl()) == null) {
                            MovieImage mi = new MovieImage();
                            mi.setMovie(movie);
                            mi.setType("backdrop");
                            mi.setSource("AZ");
                            mi.setUrl(pi.getUrl());
                            result.add(mi);
                        }
                    }
                }
            }
        }
        if (movie.getSource().equals("XXX")) {
            if (movie.getUrl() != null) {
                PicsImage[] picsImage = picsService.extractKImages(movie.getImdbid(), movie.getUrl());
                if (picsImage.length > 0) {
                    for (PicsImage pi : picsImage) {
                        if (movieImageRepository.findByUrl(movie.getId(), "backdrop", pi.getUrl()) == null) {
                            MovieImage mi = new MovieImage();
                            mi.setMovie(movie);
                            mi.setType("backdrop");
                            mi.setSource("AZ");
                            mi.setUrl(pi.getUrl());
                            result.add(mi);
                        }
                    }
                }
            }
        }
        createMovieImages(result);
        return result;
    }

    public void setImageProcessed(int imageId, String localPath){
        MovieImage movieImage = movieImageRepository.getById(imageId);
        movieImage.setProcessed("D");
        movieImage.setLocalPath(localPath);
        movieImageRepository.save(movieImage);

    }
}

