package com.spark.movie.controller;

import com.spark.movie.model.Category;
import com.spark.movie.model.MovieLanguage;
import com.spark.movie.model.YearRange;
import com.spark.movie.service.CategoryService;
import com.spark.movie.service.LanguageService;
import com.spark.movie.service.YearRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/languages")
class LanguageController {
    @Autowired
    private LanguageService languageService;

    @GetMapping
    public List<MovieLanguage> getAllLanguages() {
        return languageService.getAllLanguages();
    }

}