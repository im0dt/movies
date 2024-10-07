package com.spark.movie.service;

import com.spark.movie.model.Movie;
import com.spark.movie.model.MovieImage;
import com.spark.movie.pics.PicsImage;
import com.spark.movie.pics.PicsProcess;
import com.spark.movie.pics.PicsProperties;
import com.spark.movie.pics.PicsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class PicsService {

    @Autowired
    private PicsProperties picsProperties;

    private final WebClient webClient;

    public PicsService(PicsProperties picsProperties){
        this.picsProperties = picsProperties;
            this.webClient = WebClient.builder().baseUrl("https://api.extract.pics")//this.picsProperties.getApiUrl())
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE,
                        "Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .build();
    }

    private PicsProcess createPicsProcess(PicsRequest picsRequest){
        ResponseEntity<PicsProcess> response= null;
        try {
            response = webClient.post()
                    .uri("/v0/extractions")
                    .headers(h -> h.setBearerAuth(this.picsProperties.getApiKey()))
                    .bodyValue(picsRequest)
                    .retrieve()
                    .toEntity(PicsProcess.class)
                    .toFuture()
                    .get();
            return response.getBody();
            //                .subscribe(
//                        responseEntity -> {
//                            // Handle success response here
//
//                            String s = responseEntity.toString();
//                            // handle response as necessary
//                        },
//                        error -> {
//                            // Handle the error here
//                            if (error instanceof WebClientResponseException) {
//                                WebClientResponseException ex = (WebClientResponseException) error;
//                                HttpStatusCode status = ex.getStatusCode();
//                                System.out.println("Error Status Code: " + status.value());
//                                //...
//                            } else {
//                                // Handle other types of errors
//                                System.err.println("An unexpected error occurred: " + error.getMessage());
//                            }
//                        }
//                );
        } catch (InterruptedException e) {
            return new PicsProcess("error");
        } catch (ExecutionException e) {
            return new PicsProcess("error");
        }
    }

    private PicsProcess extratPicsProcess(String processId) {
        ResponseEntity<PicsProcess> response = null;
        try {
            response = webClient.get()
                    .uri("/v0/extractions/" + processId)
                    .headers(h -> h.setBearerAuth(this.picsProperties.getApiKey()))
                    .retrieve()
                    .toEntity(PicsProcess.class)
                    .toFuture()
                    .get();
            return response.getBody();
        } catch (InterruptedException e) {
            return new PicsProcess("stop");
        } catch (ExecutionException e) {
            return new PicsProcess("stop");
        }
    }

    private PicsImage[] extractMovieImages(PicsRequest picsRequest) {
        PicsProcess picsProcess = this.createPicsProcess(picsRequest);
        while (!picsProcess.getData().getStatus().equals("done") &&
                !picsProcess.getData().getStatus().equals("error")) {
            picsProcess = this.extratPicsProcess(picsProcess.getData().getId());
            try {
                if(picsProcess.getData().getStatus().equals("done") ||
                        picsProcess.getData().getStatus().equals("error"))
                    break;
                    Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return new PicsImage[0];
            }
        }
        return picsProcess.getData().getImages();
    }

    public PicsImage[] extractImdbImages(String imdbId) {
        PicsRequest pp = new PicsRequest(String.format(picsProperties.getImdbUrl(), imdbId));
        PicsImage[] retVal = extractMovieImages(pp);
        PicsImage[] images = Arrays.stream(retVal).filter(x -> x.getName() != null && !x.getName().contains("favicon") &&
                x.getSize() != null && !x.getName().contains(",") &&
                x.getBasename().contains("jpg") &&
                x.getWidth() > x.getHeight()).toArray(PicsImage[]::new);
        Map<String, List<PicsImage>> result
                = Arrays.stream(images).collect(
                Collectors.groupingBy(PicsImage::getbaseImegeCode));
        Map<String, PicsImage> finalImages = new HashMap<>();
        for (var entry : result.entrySet()) {
            for( var img : entry.getValue()){
                PicsImage currentImage = finalImages.get(entry.getKey());
                if( currentImage == null) {
                    finalImages.put(entry.getKey(), img);
                    currentImage = img;
                }
                if(currentImage.getWidth() < img.getWidth())
                    finalImages.put(entry.getKey(), img);
            }
        }
        return finalImages.values().toArray(new PicsImage[0]);
    }

    public PicsImage[] extractAZImages(String movieUrl) {
        PicsRequest pp = new PicsRequest(movieUrl);
        PicsImage[] retVal = extractMovieImages(pp);
        PicsImage[] images = Arrays.stream(retVal).filter(x -> x.getName() != null &&
                x.getWidth() > 1000
                ).toArray(PicsImage[]::new);
        if(images.length == 0){
            images = Arrays.stream(retVal).filter(x -> x.getName() != null &&
                    x.getWidth() > 400
            ).toArray(PicsImage[]::new);
        }
        Map<String, List<PicsImage>> result
                = Arrays.stream(images).collect(
                Collectors.groupingBy(PicsImage::getbaseImegeCode));
        Map<String, PicsImage> finalImages = new HashMap<>();
        for (var entry : result.entrySet()) {
            for( var img : entry.getValue()){
                PicsImage currentImage = finalImages.get(entry.getKey());
                if( currentImage == null) {
                    finalImages.put(entry.getKey(), img);
                    currentImage = img;
                }
                if(currentImage.getWidth() < img.getWidth())
                    finalImages.put(entry.getKey(), img);
            }
        }
        return finalImages.values().toArray(new PicsImage[0]);
    }

    public PicsImage[] extractKImages(String imdbId,String movieUrl) {
        PicsRequest pp = new PicsRequest(movieUrl);
        PicsImage[] retVal = extractMovieImages(pp);
        PicsImage[] images = Arrays.stream(retVal).filter(x -> x.getUrl().contains(imdbId) &&
                x.getWidth() !=null &&  x.getWidth() > 1000
        ).toArray(PicsImage[]::new);
        if(images.length < 5){
            images = Arrays.stream(retVal).filter(x -> x.getUrl().contains(imdbId) &&
                    x.getWidth() !=null &&  x.getWidth() >= 400
            ).toArray(PicsImage[]::new);
        }
        Map<String, List<PicsImage>> result
                = Arrays.stream(images).collect(
                Collectors.groupingBy(PicsImage::getbaseImegeCode));
        Map<String, PicsImage> finalImages = new HashMap<>();
        for (var entry : result.entrySet()) {
            for( var img : entry.getValue()){
                PicsImage currentImage = finalImages.get(entry.getKey());
                if( currentImage == null) {
                    finalImages.put(entry.getKey(), img);
                    currentImage = img;
                }
                if(currentImage.getWidth() < img.getWidth())
                    finalImages.put(entry.getKey(), img);
            }
        }
        return finalImages.values().toArray(new PicsImage[0]);
    }

}
