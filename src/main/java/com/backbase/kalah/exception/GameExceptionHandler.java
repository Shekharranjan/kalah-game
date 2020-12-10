package com.backbase.kalah.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class GameExceptionHandler is used to handle exception using AOP
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */
@Slf4j
@ControllerAdvice
public class GameExceptionHandler {

    @ResponseBody
    @ExceptionHandler(GameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    GameException gameNotFoundHandler(GameNotFoundException ex) {
        return new GameException(ex);
    }

    @ResponseBody
    @ExceptionHandler(InvalidMoveException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    GameException invalidMoveHandler(InvalidMoveException ex) {
        return new GameException(ex);
    }
}
