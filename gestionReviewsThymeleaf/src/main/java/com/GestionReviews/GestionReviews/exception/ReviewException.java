package com.GestionReviews.GestionReviews.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewException extends RuntimeException{
    public ReviewException (String message) {
        super(message);
    }
}
