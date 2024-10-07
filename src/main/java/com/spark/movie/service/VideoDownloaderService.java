package com.spark.movie.service;

import com.spark.movie.model.Movie;
import com.spark.movie.model.MovieImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class VideoDownloaderService
{
    @Autowired
    private  MovieService movieService;

    public VideoDownloaderService(MovieService movieService){
        this.movieService = movieService;
    }

    public void downloadVideos(){
        List<Movie> movies = movieService.getAllMovies();
        for(Movie movie : movies){
            List<MovieImage> videos = movieService.getMovieImages(movie.getId(), "movie");
            int i=0;
            try {
            for(MovieImage vid : videos){
                if(vid.getProcessed()!= null && vid.getProcessed().equals("D"))
                    continue;
                if( vid.getSource()!= null && vid.getSource().equals("AZ")){
                        if(!Files.exists(Path.of("D:/" + movie.getImdbid()))) {
                                Files.createDirectories(Path.of("D:/" + movie.getImdbid()));
                        }

                        BufferedInputStream in = new BufferedInputStream(new URL(vid.getUrl()).openStream());

                         FileOutputStream fileOutputStream = new FileOutputStream("D:/" + movie.getImdbid() + "/AZ" + i + ".mp4");
                        int completed = 0;
                         byte dataBuffer[] = new byte[819200];
                        int bytesRead;
                        while ((bytesRead = in.read(dataBuffer, 0, 819200)) != -1) {
                            fileOutputStream.write(dataBuffer, 0, bytesRead);
                            completed = completed + 100;
                                System.out.println("Movie " + movie.getName() + " - AZ" + i + ", " + (completed /10000) + " KB");
                        }
                        movieService.setImageProcessed(vid.getId(), "/AZ" + i + ".mp4");
                        i++;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
