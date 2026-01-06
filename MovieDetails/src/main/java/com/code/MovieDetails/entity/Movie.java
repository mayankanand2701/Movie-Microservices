package com.code.MovieDetails.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Movie {

    private Long id;

    @NotBlank(message = "Movie name must not be blank")
    @Size(min = 2, max = 100, message = "Movie name must be between 2 and 100 characters")
    private String movieName;

    @NotBlank(message = "Release year is required")
    @Pattern(regexp = "^[0-9]{4}$", message = "Release year must be a 4-digit year")
    private String releaseYear;

    @NotBlank(message = "Cast information is required")
    private String cast;

    @NotNull(message = "Release date is required")
    private LocalDate releaseDate;
}
