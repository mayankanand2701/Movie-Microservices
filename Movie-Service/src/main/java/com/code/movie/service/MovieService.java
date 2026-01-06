package com.code.movie.service;

import com.code.movie.entity.Movie;
import com.code.movie.exceptions.ResourceNotFoundException;
import com.code.movie.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public Mono<Movie> createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Flux<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    public Mono<Movie> getAMovie(Long id) {
        return movieRepository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ResourceNotFoundException("Movie not found with id " + id)));
    }

    public Mono<Movie> updateAMovie(Long id, Movie movie) {
        return movieRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new ResourceNotFoundException(
                                "Movie not found with id: " + id
                        ))
                )
                .flatMap(existingMovie -> {
                    movie.setId(existingMovie.getId());
                    return movieRepository.save(movie);
                });
    }

    public Mono<String> deleteById(Long id) {
        return movieRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ResourceNotFoundException(
                                "Movie not found with id: " + id));
                    }
                    return movieRepository.deleteById(id)
                            .thenReturn("Movie deleted successfully with id: " + id);
                });
    }
}
