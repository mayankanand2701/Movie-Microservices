package com.code.MovieDetails.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDetails {
    private Long id;
    private String movieName;
    private String releaseYear;
    private String cast;
    private LocalDate releaseDate;
    private List<MovieReview> reviews;
}
