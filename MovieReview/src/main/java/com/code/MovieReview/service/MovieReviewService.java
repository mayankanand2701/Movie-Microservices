package com.code.MovieReview.service;

import com.code.MovieReview.entity.MovieReview;
import com.code.MovieReview.exceptions.ResourceNotFoundException;
import com.code.MovieReview.repository.MovieReviewRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieReviewService {

    @Autowired
    MovieReviewRepository movieReviewRepository;

    public Mono<MovieReview> createReview(MovieReview movieReview) {
        return movieReviewRepository.save(movieReview);
    }

    public Flux<MovieReview> getAllReviews() {
        return movieReviewRepository.findAll();
    }

    public Mono<MovieReview> getAReview(Long id) {
        return movieReviewRepository.findById(id)
                .switchIfEmpty(Mono.error(
                new ResourceNotFoundException("Review not found with id " + id)));
    }

    public Mono<MovieReview> updateAReview(Long id,MovieReview movieReview) {
        return movieReviewRepository.findById(id)
                .switchIfEmpty(
                        Mono.error(new ResourceNotFoundException(
                                "Review not found with id: " + id
                        ))
                )
                .flatMap(existingReview -> {
                    movieReview.setId(existingReview.getId());
                    return movieReviewRepository.save(movieReview);
                });
    }

    public Mono<String> deleteById(Long id) {
        return movieReviewRepository.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new ResourceNotFoundException(
                                "Review not found with id: " + id));
                    }
                    return movieReviewRepository.deleteById(id)
                            .thenReturn("Review deleted successfully with id: " + id);
                });
    }

    public Flux<MovieReview> getReviewsByMovieId(Long movieId) {
        return movieReviewRepository.findByMovieId(movieId);
    }
}
