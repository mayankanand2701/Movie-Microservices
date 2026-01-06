package com.code.MovieDetails.client;

import com.code.MovieDetails.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ReviewsRestClient {
    @Autowired
    private WebClient webClient;

    @Value("${review.service.base-url}")
    private String reviewServiceBaseUrl;

    public Flux<MovieReview> retrieveReviewsByMovieId(Long movieId) {
        String url = reviewServiceBaseUrl + "/{movieId}";
        return webClient
                .get()
                .uri(url, movieId)
                .retrieve()
                .bodyToFlux(MovieReview.class)
                .log();
    }
}
