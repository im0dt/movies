package com.spark.movie.controller;

import com.spark.movie.model.MovieImage;
import com.spark.movie.pics.PicsImage;
import com.spark.movie.pics.PicsRequest;
import com.spark.movie.service.MovieService;
import com.spark.movie.service.PicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pics")
public class picsController {
    @Autowired
    MovieService movieService;
    @GetMapping("/{id}")
    public List<MovieImage> get(@PathVariable int id) {
        return movieService.extractMovieImages(id);
    }
}
