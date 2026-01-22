package com.finfactor.omdb.exception;

public class OmdbApiException extends RuntimeException {

    public OmdbApiException(String message) {
        super(message);
    }

    public OmdbApiException(String message, Throwable cause) {
        super(message, cause);
    }
}