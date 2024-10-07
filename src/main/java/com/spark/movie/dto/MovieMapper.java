package com.spark.movie.dto;

import com.spark.movie.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    private ModelMapper mapper;
    MovieMapper(){
        mapper = new ModelMapper();
    }

    public MovieDTO createMovieDTO(Movie movie) {
        return this.mapper.map(movie, MovieDTO.class);
    }

    public Movie map(MovieDTO source, Movie destination) {
        this.mapper.map(source, destination);
        return destination;
    }
}
