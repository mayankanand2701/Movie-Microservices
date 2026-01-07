package com.code.MovieDetails.client;

import com.code.MovieDetails.entity.Movie;
import com.code.MovieDetails.exception.MovieNotFoundException;
import com.code.MovieDetails.exception.MovieServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class MovieInfoRestClient {

    @Autowired
    private WebClient webClient;

    @Value("${movie.service.base-url}")
    private String movieServiceBaseUrl;

    public Mono<Movie> retrieveMovieInfo(Long id) {
        var url = movieServiceBaseUrl.concat("/{id}");

        return webClient
                .get()
                .uri(url, id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> {
                    if (response.statusCode() == HttpStatus.NOT_FOUND) {
                        return Mono.error(
                                new MovieNotFoundException(
                                        "Movie not found with id: " + id
                                )
                        );
                    }
                    return Mono.error(
                            new MovieServiceException(
                                    "Client error while calling Movie Service"
                            )
                    );
                })
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(
                                new MovieServiceException(
                                        "Movie Service is unavailable. Please try later."
                                )
                        )
                )
                .bodyToMono(Movie.class);
    }
}
