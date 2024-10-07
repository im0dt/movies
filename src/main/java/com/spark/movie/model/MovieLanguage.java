package com.spark.movie.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
public class MovieLanguage {

    public MovieLanguage(String language) {
        this.language = language.split("\r\n")[0];
    }

    private String language;

    public String getLanguage() {
        return language;
    }
}
