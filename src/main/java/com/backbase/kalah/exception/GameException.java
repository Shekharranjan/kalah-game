package com.backbase.kalah.exception;

/**
 * Exception DTO used to send response in case of any error
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */
public class GameException  {

    private String errorCode;
    private String description;

    public GameException(KalahGameException ex) {
        this.errorCode = ex.getErrorCode();
        this.description = ex.getDescription();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

}
