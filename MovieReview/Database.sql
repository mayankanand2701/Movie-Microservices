create database movie;
use movie;

CREATE TABLE movie_reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    reviewer_name VARCHAR(100) NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    review_text VARCHAR(1000) NOT NULL
);
