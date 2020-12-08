package com.backbase.kalah.exception;

/**
 * Exception class to handle Game related exceptions
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

public class GameNotFoundException extends KalahGameException {
    public GameNotFoundException(String errorCode, String description) {
        super(errorCode, description);
    }
}
