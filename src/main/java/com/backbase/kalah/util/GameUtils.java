package com.backbase.kalah.util;

import com.backbase.kalah.model.Game;
import com.backbase.kalah.model.KalahGame;
import com.backbase.kalah.model.Pit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility class to help Game operations
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */


@Component
public class GameUtils {

    @Value("${server.port}")
    String port;

    /**
     * Adaptor method to convert Game object to KalahGame Object
     *
     * @param game current game
     * @param uri  game url
     * @return KalahGame object
     */
    public KalahGame getKalahGame(Game game, String uri) {
        Map<Integer, Integer> status = game.getGameBoard().getPits().stream()
                .collect(Collectors.toMap(Pit::getId, Pit::getNoOfStones));
        return new KalahGame(game.getId(), uri, status, game.getPlayerTurn(), game.getWinner());
    }

    /**
     * Method used to generate game url
     *
     * @param gameId game id
     * @return game url
     */
    public String generateGameURL(final String gameId) {
        return String.format("http://%s:%s/games/%s", InetAddress.getLoopbackAddress().getHostName(),
                port, gameId);
    }
}
