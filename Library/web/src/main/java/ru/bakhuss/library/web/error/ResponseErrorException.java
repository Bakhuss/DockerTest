package ru.bakhuss.library.web.error;

public class ResponseErrorException extends RuntimeException {

    public ResponseErrorException(String error) {
        super(error);
    }
}
