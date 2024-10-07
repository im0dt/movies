package com.spark.movie.service;

import com.spark.movie.tmdb.model.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutionException;

@Service
public class THMDBService {
    private final WebClient webClient;

    public THMDBService(){
        this.webClient = WebClient.builder().baseUrl("https://api.themoviedb.org/3")//this.picsProperties.getApiUrl())
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE,
                        "Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public THMDBMovie findMovie(String imdbId){
        THMDBFindResponse response = new THMDBFindResponse();
        try {
            response = webClient.get()
                    .uri( "/find/tt" + imdbId + "?external_source=imdb_id&api_key=" + "15d2ea6d0dc1d476efbca3eba2b9bbfb")
                    .retrieve()
                    .toEntity(THMDBFindResponse.class)
                    .toFuture()
                    .get()
                    .getBody();
            return response.getMovie_results().get(0);
        } catch (InterruptedException e) {
            return new THMDBMovie();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public THMDBImageResponse extractMovieImage(String imdbId){
        THMDBMovie movie = findMovie(imdbId);
        THMDBImageResponse response = new THMDBImageResponse();
        try {
            response = webClient.get()
                    .uri( "/movie/" + movie.getId() + "/images?api_key=" + "15d2ea6d0dc1d476efbca3eba2b9bbfb")
                    .retrieve()
                    .toEntity(THMDBImageResponse.class)
                    .toFuture()
                    .get().getBody();
            return response;
        } catch (InterruptedException e) {
            return response;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public THMDBVideoResponse extractMovieVideo(String imdbId){
        THMDBMovie movie = findMovie(imdbId);
        THMDBVideoResponse response = new THMDBVideoResponse();
        try {
            response = webClient.get()
                    .uri( "/movie/" + movie.getId() + "/videos?api_key=" + "15d2ea6d0dc1d476efbca3eba2b9bbfb")
                    .retrieve()
                    .toEntity(THMDBVideoResponse.class)
                    .toFuture()
                    .get().getBody();
            return response;
        } catch (InterruptedException e) {
            return response;
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
