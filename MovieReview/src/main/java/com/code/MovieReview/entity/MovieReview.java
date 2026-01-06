package com.code.MovieReview.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "movie_reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieReview {

    @Id
    private Long id;

    @NotNull(message = "Movie ID is required")
    @Column("movie_id")
    private Long movieId;

    @NotBlank(message = "Reviewer name must not be blank")
    @Size(min = 2, max = 100)
    @Column("reviewer_name")
    private String reviewerName;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column("rating")
    private Integer rating;

    @NotBlank(message = "Review text must not be blank")
    @Size(max = 1000, message = "Review cannot exceed 1000 characters")
    @Column("review_text")
    private String reviewText;
}
