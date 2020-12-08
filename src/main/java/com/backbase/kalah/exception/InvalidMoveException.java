package com.backbase.kalah.exception;

/**
 * Exception class to handle Game related exceptions
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

public class InvalidMoveException extends KalahGameException {

    public InvalidMoveException(String errorCode, String description) {
        super(errorCode, description);
    }
}
