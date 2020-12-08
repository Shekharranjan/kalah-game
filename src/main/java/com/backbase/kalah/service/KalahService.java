package com.backbase.kalah.service;

import com.backbase.kalah.model.Game;

/**
 * This Service Interface is meant to serve games different actions
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

public interface KalahService {
    /**
     * Method to create new Kalah Game
     *
     * @return new Kalah Game
     */
    public Game createGame();

    /**
     * Method to play Kalah Game
     *
     * @param gameId current game id
     * @param pitId pit number to start the move
     * @return current Kalah Game with latest status
     */
    public Game makeMove(final String gameId, final Integer pitId);

    /**
     * Method to load Game from Database
     *
     * @param gameId current game id
     * @return current Game
     */
    public Game loadGame(final String gameId);
}
