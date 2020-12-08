package com.backbase.kalah.rule;

import com.backbase.kalah.model.Game;

/*
 * Interface to apply all game rules
 *
 *@author Chandra Ranjan
 *@since 05-12-2020
 */
public interface KalahGameRule {

    /**
     * Method to apply all the Kalah Rule to make the move
     * @param game Current game object
     * @param pitId Pit selected to move stones
     */
    void applyGameRule(Game game, Integer pitId);
}
