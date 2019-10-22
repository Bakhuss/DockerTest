package ru.bakhuss.library.web.error;

public class ResponseError {
    public String error;

    public ResponseError(String errMsg) {
        this.error = errMsg;
    }
}
