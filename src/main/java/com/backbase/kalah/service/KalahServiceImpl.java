package com.backbase.kalah.service;

import com.backbase.kalah.exception.GameNotFoundException;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.repository.KalahGameRepository;
import com.backbase.kalah.rule.KalahGameRule;
import com.backbase.kalah.rule.KalahGameRuleRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Class to fulfill all  operations of the Game
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

@Slf4j
@Service
public class KalahServiceImpl implements KalahService {

    @Autowired
    KalahGameRepository kalahGameRepository;
    @Autowired
    KalahGameRule kalahGameRuleRunner;

    /**
     * Method to create new Kalah Game
     *
     * @return newly created Kalah Game
     */
    @Override
    public Game createGame() {
        return kalahGameRepository.save(new Game());
    }

    /**
     * Method to load Game from Database
     *
     * @param gameId current game id
     * @return current Game
     */
    @Override
    public Game loadGame(String gameId) {
        return kalahGameRepository.findById(gameId)
                .orElseThrow(() -> new GameNotFoundException("ERR_GAME_NOT_FOUND", "Game " + gameId + " not found."));
    }

    /**
     * Method to make move in the game
     *
     * @param pitId  pit id selected by the player
     * @param gameId game id for which want to make move
     * @return current game status after the latest move
     */
    @Override
    public Game makeMove(String gameId, Integer pitId) {
        log.info("Searching for game - {}", gameId);
        Game game = loadGame(gameId);
        log.debug("Found game - {}", game.getId());
        kalahGameRuleRunner.applyGameRule(game, pitId);
        return kalahGameRepository.save(game);
    }
}
