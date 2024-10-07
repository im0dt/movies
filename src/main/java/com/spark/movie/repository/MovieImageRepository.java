package com.spark.movie.repository;

import com.spark.movie.model.Movie;
import com.spark.movie.model.MovieImage;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieImageRepository extends JpaRepository<MovieImage, Integer> {
    @Query(value = "SELECT mi FROM MovieImage mi WHERE mi.movie.id = ?1 and mi.type= ?2")
    List<MovieImage> listMovieImages(int movieId, String type);

    @Query("SELECT mi FROM MovieImage mi WHERE mi.movie.id = ?1 and mi.type= ?2 and mi.url=?3")
    MovieImage findByUrl(int movieId, String type, String url);
}
