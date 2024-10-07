package com.spark.movie.controller;

import com.spark.movie.dto.MovieMapper;
import com.spark.movie.model.Movie;
import com.spark.movie.dto.MovieDTO;
import com.spark.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
class MoviesController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieMapper movieMapper;
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable int id) {

        return ResponseEntity.ok(movieMapper.createMovieDTO(movieService.getMovieById(id).get()));
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity replaceEmployee(@RequestBody MovieDTO newMovie, @PathVariable int id) {
            Movie movie = movieService.getMovieById(id).get();
            movie = movieMapper.map(newMovie, movie);
            movieService.updateMovie(movie);
            return ResponseEntity.ok().build();
    }

//    @RequestMapping(value = "/updateImages", produces = "application/json",  method=RequestMethod.POST)
//    public Movie updateImages(@RequestBody Movie movie) {
//        Movie myMovie = movieRepository.findByImdbId(movie.getImdbid());
//        myMovie.setPoster(movie.getPoster());
//        myMovie.setBackdrop(movie.getBackdrop());
//        movieRepository.save(myMovie);;
//        return myMovie;
//    }

}