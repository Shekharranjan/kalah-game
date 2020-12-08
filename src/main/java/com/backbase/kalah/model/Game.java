package com.backbase.kalah.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Class to represent Kalah Game
 * Same will be used to persist the game into MongoDB
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

@Document
public class Game {

    @Id
    private String id;
    private Board gameBoard;
    private Player playerTurn;
    private Player winner;

    public Game() {
        this.gameBoard = new Board();
        this.playerTurn =  Player.PLAYER_ONE;
    }


    public Game(Board gameBoard, Player playerTurn, Player winner) {
        this.gameBoard = gameBoard;
        this.playerTurn = playerTurn;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
