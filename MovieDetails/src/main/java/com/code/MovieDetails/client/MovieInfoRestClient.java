package com.code.MovieDetails.client;

import com.code.MovieDetails.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        var url=movieServiceBaseUrl.concat("/{id}");
        return webClient
                .get()
                .uri(url,id)
                .retrieve()
                .bodyToMono(Movie.class)
                .log();

    }
}
