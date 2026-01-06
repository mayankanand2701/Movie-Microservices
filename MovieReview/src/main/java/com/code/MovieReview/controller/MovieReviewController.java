package com.code.MovieReview.controller;

import com.code.MovieReview.entity.MovieReview;
import com.code.MovieReview.service.MovieReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/review")
public class MovieReviewController {

    @Autowired
    MovieReviewService movieReviewService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieReview> createReview(@Valid @RequestBody MovieReview movieReview) {
        return movieReviewService.createReview(movieReview);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MovieReview> getAllReviews() {
        return movieReviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MovieReview> getAReview(@PathVariable Long id) {
        return movieReviewService.getAReview(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MovieReview> updateAReview(@PathVariable Long id,@Valid @RequestBody MovieReview movieReview){
        return movieReviewService.updateAReview(id,movieReview);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable Long id) {
        return movieReviewService.deleteById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/movie/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<MovieReview> getReviewsByMovieId(
            @PathVariable Long movieId) {
        return movieReviewService.getReviewsByMovieId(movieId);
    }
}
