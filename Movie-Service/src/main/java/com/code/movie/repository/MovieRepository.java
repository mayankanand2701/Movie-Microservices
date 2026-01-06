package com.code.movie.repository;

import com.code.movie.entity.Movie;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends R2dbcRepository<Movie,Long> {
}
