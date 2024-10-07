package com.spark.movie.controller;

import com.spark.movie.model.Movie;
import com.spark.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/movieLanguages")
public class MovieLanguageController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getMovieLanguagesById(@RequestParam("lang") String lang, @RequestParam("count") int count) {
       return movieService.getAllMoviesByLanguage(lang, count);
    }
}
