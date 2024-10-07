package com.spark.movie.service;

import com.spark.movie.model.Category;
import com.spark.movie.model.MovieLanguage;
import com.spark.movie.repository.CategoryRepository;
import com.spark.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class LanguageService {

    private final MovieRepository movieRepository;

    @Autowired
    public LanguageService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieLanguage> getAllLanguages() {
        return movieRepository.findAllLanguages().stream()
                .collect(groupingBy(MovieLanguage::getLanguage)).entrySet().stream()
                .map(e ->
                     new MovieLanguage(e.getKey())

                ).collect(Collectors.toList());
    }
}
