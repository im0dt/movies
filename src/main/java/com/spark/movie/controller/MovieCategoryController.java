package com.spark.movie.controller;

import com.spark.movie.model.Movie;
import com.spark.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/movieCategories")
public class MovieCategoryController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public List<Movie> getMovieCategoriesById(@PathVariable int id, @RequestParam("count") int count) {
       return movieService.getAllMoviesByCategoryId(id, count);
    }
}
