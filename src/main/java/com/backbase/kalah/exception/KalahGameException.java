package com.backbase.kalah.exception;

public class KalahGameException extends RuntimeException {

    private String errorCode;
    private String description;

    public KalahGameException(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

}
