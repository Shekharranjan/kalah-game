package com.backbase.kalah.model;

import com.backbase.kalah.util.Constants;

/**
 * Class to hold pit's detail
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

public class Pit {

    private int id;
    private int noOfStones;

    public Pit() {
    }

    public Pit(int id) {
        this.id = id;
        if (!this.isKalahHouse())
            this.noOfStones = 6;
    }

    public Pit(int id, int noOfStones) {
        this.id = id;
        this.noOfStones = noOfStones;
    }

    /**
     * Checking if current selected pit is a Kalah House
     *
     * @return true if its a Kalah House else false
     */
    public boolean isKalahHouse() {
        return (this.getId() == Constants.PLAYER1_KALAH_HOUSE_PIT_ID) || (this.getId() == Constants.PLAYER2_KALAH_HOUSE_PIT_ID);
    }

    /**
     * Method to find Pits Owner
     *
     * @return Pit's Owner
     */
    public Player getOwner() {
        if (this.getId() <= Constants.PLAYER1_KALAH_HOUSE_PIT_ID)
            return Player.PLAYER_ONE;
        else
            return Player.PLAYER_TWO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfStones() {
        return noOfStones;
    }

    public void setNoOfStones(int noOfStones) {
        this.noOfStones = noOfStones;
    }
}
