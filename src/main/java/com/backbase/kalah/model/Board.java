package com.backbase.kalah.model;

import com.backbase.kalah.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to present Kalah Game Board
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

public class Board {

    private List<Pit> pits;

    public Board() {
        this.pits = new ArrayList<>();
        for (int i = Constants.START_PIT_ID; i <= Constants.LAST_PIT_ID; i++) {
            this.pits.add(new Pit(i));
        }
    }

    public Board(List<Pit> pits) {
        this.pits = pits;
    }

    public Pit getPit(final int index) {
        return this.pits.get((index - 1) % Constants.LAST_PIT_ID);
    }

    public List<Pit> getPits() {
        return pits;
    }

    public void setPits(List<Pit> pits) {
        this.pits = pits;
    }

    public int getPlayerPitsStones(Player player) {
        return this.getPits().stream()
                .filter(pit -> (pit.getOwner().equals(player)))
                .filter(pit->!pit.isKalahHouse())
                .mapToInt(Pit::getNoOfStones).sum();
    }
}
