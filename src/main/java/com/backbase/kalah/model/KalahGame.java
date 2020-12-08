package com.backbase.kalah.model;

import java.util.Map;

/**
 * Class DTO used for responding a request
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */
public class KalahGame {

    private final String id;
    private final String uri;
    private final Map<Integer, Integer> status;
    private final  Player playerTurn;
    private final  Player winner;

    public KalahGame(String id, String uri, Map<Integer, Integer> status, Player playerTurn, Player winner) {
        this.id = id;
        this.uri = uri;
        this.status = status;
        this.playerTurn = playerTurn;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public Map<Integer, Integer> getStatus() {
        return status;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public Player getWinner() {
        return winner;
    }
}
