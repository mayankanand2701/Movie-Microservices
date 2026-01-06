package com.code.movie.exceptions;
import com.code.movie.entity.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ApiError>> handleValidationException(
            WebExchangeBindException ex,
            ServerWebExchange exchange
    ) {

        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Invalid request data",
                exchange.getRequest().getPath().value(),
                errors
        );
        return Mono.just(ResponseEntity.badRequest().body(apiError));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<ApiError>> handleNotFound(
            ResourceNotFoundException ex,
            ServerWebExchange exchange
    ) {
        ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                exchange.getRequest().getPath().value(),
                null
        );
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ApiError>> handleGeneric(
            Exception ex,
            ServerWebExchange exchange
    ) {
        ApiError apiError = new ApiError(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                exchange.getRequest().getPath().value(),
                null
        );
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError));
    }
}