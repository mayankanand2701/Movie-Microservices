create database movie;
use movie;

CREATE TABLE movies (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  movie_name VARCHAR(255) NOT NULL,
  release_year VARCHAR(4) NOT NULL,
  cast_json JSON,
  release_date DATE
);

