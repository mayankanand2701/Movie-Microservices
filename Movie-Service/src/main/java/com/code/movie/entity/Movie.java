package com.code.movie.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Table(name="movies")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {

    @Id
    private Long id;

    @NotBlank(message = "Movie name must not be blank")
    @Size(min = 2, max = 100, message = "Movie name must be between 2 and 100 characters")
    @Column("movie_name")
    private String movieName;

    @NotBlank(message = "Release year is required")
    @Pattern(regexp = "^[0-9]{4}$", message = "Release year must be a 4-digit year")
    @Column("release_year")
    private String releaseYear;

    @NotBlank(message = "Cast information is required")
    @Column("cast_json")
    private String cast;

    @NotNull(message = "Release date is required")
    @Column("release_date")
    private LocalDate releaseDate;
}
