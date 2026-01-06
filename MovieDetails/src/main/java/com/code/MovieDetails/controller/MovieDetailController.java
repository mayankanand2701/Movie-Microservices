package com.code.MovieDetails.controller;

import com.code.MovieDetails.client.MovieInfoRestClient;
import com.code.MovieDetails.client.ReviewsRestClient;
import com.code.MovieDetails.entity.Movie;
import com.code.MovieDetails.entity.MovieDetails;
import com.code.MovieDetails.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/movie-details")
public class MovieDetailController {

    @Autowired
    MovieInfoRestClient movieInfoRestClient;
    @Autowired
    ReviewsRestClient reviewsRestClient;

    @GetMapping("/{id}")
    public Mono<MovieDetails> retrieveMovieById(@PathVariable Long id) {

        return movieInfoRestClient.retrieveMovieInfo(id)
                .flatMap(movie ->
                        reviewsRestClient.retrieveReviewsByMovieId(id)
                                .collectList()
                                .map(reviews ->
                                        new MovieDetails(
                                                movie.getId(),
                                                movie.getMovieName(),
                                                movie.getReleaseYear(),
                                                movie.getCast(),
                                                movie.getReleaseDate(),
                                                reviews
                                        )
                                )
                )
                .switchIfEmpty(
                        Mono.error(new RuntimeException("Movie not found with id " + id))
                );
    }
}
