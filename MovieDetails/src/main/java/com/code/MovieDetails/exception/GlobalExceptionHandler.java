package com.code.MovieDetails.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MovieNotFoundException.class)
    public Mono<Map<String, Object>> handleMovieNotFound(MovieNotFoundException ex) {
        return Mono.just(
                Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(MovieServiceException.class)
    public Mono<Map<String, Object>> handleMovieServiceError(MovieServiceException ex) {
        return Mono.just(
                Map.of(
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "error", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public Mono<Map<String, Object>> handleReviewNotFound(ReviewNotFoundException ex) {
        return Mono.just(
                Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "error", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(ReviewServiceException.class)
    public Mono<Map<String, Object>> handleReviewServiceError(ReviewServiceException ex) {
        return Mono.just(
                Map.of(
                        "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "error", ex.getMessage()
                )
        );
    }
}

