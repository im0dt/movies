package com.spark.movie.controller;

import com.spark.movie.model.Category;
import com.spark.movie.model.YearRange;
import com.spark.movie.service.CategoryService;
import com.spark.movie.service.YearRangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/api/yearranges")
class YearRangeController {
    @Autowired
    private YearRangeService yearRangeService;

    @GetMapping
    public List<YearRange> getAllYearRanges() {
        return yearRangeService.getAllYearRanges();
    }

}