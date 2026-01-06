package com.code.MovieReview.repository;

import com.code.MovieReview.entity.MovieReview;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovieReviewRepository extends R2dbcRepository<MovieReview,Long> {
    Flux<MovieReview> findByMovieId(Long movieId);
}
