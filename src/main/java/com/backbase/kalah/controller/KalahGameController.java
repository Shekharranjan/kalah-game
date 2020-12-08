package com.backbase.kalah.controller;

import com.backbase.kalah.model.Game;
import com.backbase.kalah.model.KalahGame;
import com.backbase.kalah.service.KalahService;
import com.backbase.kalah.util.GameUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Kalah Game Controller Class to handle creation and play moves of the game
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */
@Slf4j
@RestController
public class KalahGameController {
    private  final String URI_GAME = "/games";
    private  final String URI_GAME_MOVE = URI_GAME + "/{gameId}/pits/{pitId}";

    @Autowired
    KalahService kalahService;

    @Autowired
    GameUtils gameUtils;

    /**
     * This method will create a new Kalah Game
     * @return the kalah game board
     */
    @PostMapping(URI_GAME)
    @ApiOperation(value = "Endpoint to create New Kalah Game",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
            response = KalahGame.class, httpMethod = "POST")
    public ResponseEntity<KalahGame> createGame(){
        log.info("Creating New Kalah Game... ");
        Game newGame = kalahService.createGame();
        log.info("New Kalah Game - {} created ... ", newGame.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(gameUtils.getKalahGame(newGame,gameUtils.generateGameURL(newGame.getId())));
    }

    /**
     * Moves the stones from selected pit to other pits as per game rule
     *
     * @param pitId  pit id selected by the player
     * @param gameId game id for which want to make move
     * @return the kalah game
     */
    @PutMapping(URI_GAME_MOVE)
    @ApiOperation(value = "Endpoint to make move in the game and decide on winner.",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE, response = KalahGame.class, httpMethod = "PUT")
    public ResponseEntity<KalahGame> makeMove(@PathVariable final String gameId, @PathVariable final Integer pitId) {
        log.debug("Making move for gameId {} and pitId {}",gameId,pitId);
        Game game  =  kalahService.makeMove(gameId,pitId);
        return ResponseEntity.status(HttpStatus.OK).body(gameUtils.getKalahGame(game,gameUtils.generateGameURL(game.getId())));
    }
}
