package com.spark.movie.service;

import com.spark.movie.model.Category;
import com.spark.movie.model.YearRange;
import com.spark.movie.repository.CategoryRepository;
import com.spark.movie.repository.YearRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YearRangeService {

    private final YearRangeRepository yearRangeRepository;

    @Autowired
    public YearRangeService(YearRangeRepository yearRangeRepository) {
        this.yearRangeRepository = yearRangeRepository;
    }

    public List<YearRange> getAllYearRanges() {
        return yearRangeRepository.findAll();
    }


}

