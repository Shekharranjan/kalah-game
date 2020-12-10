package com.backbase.kalah.exception;

/**
 * Custom Exception class to handle Game related exceptions
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

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
