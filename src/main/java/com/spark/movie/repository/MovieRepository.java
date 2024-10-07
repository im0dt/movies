package com.spark.movie.repository;

import com.spark.movie.model.Movie;
import com.spark.movie.model.MovieLanguage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "SELECT mc.movie FROM MovieCategory mc WHERE mc.category.id = ?1")
    List<Movie> findByCategory(int categoryId, Pageable pageable);

    @Query(value = "SELECT mc FROM Movie mc WHERE mc.languages like :lang%")
    List<Movie> findByLanguage(String lang, Pageable pageable);

    @Query(value = "SELECT m FROM Movie m WHERE m.year >= ?1 and m.year <= ?2")
    List<Movie> findByRange(int from, int to, Pageable pageable);
    @Query(value = "SELECT m FROM Movie m WHERE m.imdbid = ?1")
    Movie findByImdbId(String imdbId);

    @Query(value =
            "SELECT "+
                    " new com.spark.movie.model.MovieLanguage(m.languages) " +
                    " FROM Movie m" +
                    " GROUP BY m.languages"
    )
    List<MovieLanguage> findAllLanguages();

}
