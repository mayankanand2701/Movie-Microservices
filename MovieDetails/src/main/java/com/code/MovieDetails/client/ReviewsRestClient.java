package com.code.MovieDetails.client;

import com.code.MovieDetails.entity.MovieReview;
import com.code.MovieDetails.exception.ReviewNotFoundException;
import com.code.MovieDetails.exception.ReviewServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return reactor.core.publisher.Mono.error(
                                new ReviewNotFoundException(
                                        "No reviews found for movie id: " + movieId
                                )
                        );
                    }
                    return reactor.core.publisher.Mono.error(
                            new ReviewServiceException(
                                    "Client error while calling Review Service"
                            )
                    );
                })
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        reactor.core.publisher.Mono.error(
                                new ReviewServiceException(
                                        "Review Service is unavailable. Please try later."
                                )
                        )
                )
                .bodyToFlux(MovieReview.class);

    }
}
