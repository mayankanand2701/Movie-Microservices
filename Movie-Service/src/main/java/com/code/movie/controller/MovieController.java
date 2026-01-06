package com.code.movie.controller;

import com.code.movie.entity.Movie;
import com.code.movie.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Movie> createMovie(@Valid @RequestBody Movie movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Movie> getAllMovie() {
        return movieService.getAllMovie();
    }

    @GetMapping("/movie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Movie> getAMovie(@PathVariable Long id) {
        return movieService.getAMovie(id);
    }

    @PutMapping("/movie/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Movie> updateAMovie(@PathVariable Long id,@Valid @RequestBody Movie movie){
        return movieService.updateAMovie(id,movie);
    }

    @DeleteMapping("/movie/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Long id) {
        return movieService.deleteById(id)
                .map(ResponseEntity::ok);
    }
}
