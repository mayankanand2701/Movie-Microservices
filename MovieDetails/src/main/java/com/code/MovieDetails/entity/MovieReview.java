package com.code.MovieDetails.entity;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class MovieReview {

    private Long id;

    @NotNull(message = "Movie ID is required")
    private Long movieId;

    @NotBlank(message = "Reviewer name must not be blank")
    @Size(min = 2, max = 100)
    private String reviewerName;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    @NotBlank(message = "Review text must not be blank")
    @Size(max = 1000, message = "Review cannot exceed 1000 characters")
    private String reviewText;
}
