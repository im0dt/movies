package com.spark.movie;

import com.spark.movie.pics.PicsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties(PicsProperties.class)
@ConfigurationPropertiesScan("application.properties")
public class MovieApplication {

	public static void main(String[] args) {

		SpringApplication.run(MovieApplication.class, args);

	}



}
