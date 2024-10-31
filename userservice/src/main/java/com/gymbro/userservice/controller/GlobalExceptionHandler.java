package com.gymbro.userservice.controller;

import com.gymbro.userservice.exceptions.BadUserIdentifer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ProblemDetail handleUserIdentifierNotFoundException(BadUserIdentifer exception, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
        problemDetail.setInstance(URI.create(request.getDescription(false).replace("uri=", "")));

        log.error(problemDetail.toString());

        return problemDetail;
    }
}
