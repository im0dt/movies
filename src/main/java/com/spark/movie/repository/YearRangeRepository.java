package com.spark.movie.repository;

import com.spark.movie.model.Category;
import com.spark.movie.model.YearRange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRangeRepository extends JpaRepository<YearRange, Integer> {
}
