package com.backbase.kalah.rule;

import com.backbase.kalah.exception.InvalidMoveException;
import com.backbase.kalah.model.Game;
import com.backbase.kalah.model.Pit;
import com.backbase.kalah.model.Player;
import com.backbase.kalah.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * KalahGameRuleRunner Class is to play the game by Kalah Rules
 *
 * @author Chandra Ranjan
 * @since 05-12-2020
 */

@Slf4j
@Component
public class KalahGameRuleRunner implements KalahGameRule{

    /**
     * Main rule method to apply all the Kalah Rule to make the move
     *
     * @param pitId  pit id selected by the player
     * @param game  game object for which want to make move
     */
    @Override
    public void applyGameRule(Game game, Integer pitId ){
        log.info("Distributing stones....");

        if(isValiddMove(game,pitId )){
            applyStoneDistributionRule(game,pitId);
            applyGameFinishingRule(game);
       }
    }

    public void applyStoneDistributionRule(Game game, Integer pitId ){
        log.info("Distributing stones....");

        Pit pit = game.getGameBoard().getPit(pitId);
        Integer stonesInPit = pit.getNoOfStones();
        pit.setNoOfStones(0);
        for(int i=0; i<stonesInPit; i++){
            Pit nextPit = game.getGameBoard().getPit(++pitId);
            nextPit.setNoOfStones(nextPit.getNoOfStones()+1);
        }
        log.info("Distribution Done.... Now checking if Move finish at player's empty pit....");
        applyMoveFinishingOwnEmptyPitRule(game,pitId);
        log.info("Setting next player turn..");
        applyNextPlayerTurnRule(game,pitId);
    }


    /**
     * Method to decide on player turn
     *
     * @param game game id
     */
    private void applyNextPlayerTurnRule(Game game, Integer pitId){
        Pit pit = game.getGameBoard().getPit(pitId);
        if(pit.isKalahHouse()){
            if(pit.getOwner().equals(Player.PLAYER_ONE) && game.getPlayerTurn().equals(Player.PLAYER_ONE))
                game.setPlayerTurn(Player.PLAYER_ONE);
            else if(pit.getOwner().equals(Player.PLAYER_TWO) && game.getPlayerTurn().equals(Player.PLAYER_TWO))
                game.setPlayerTurn(Player.PLAYER_TWO);
        }
        else{
            if(game.getPlayerTurn().equals(Player.PLAYER_ONE))
                game.setPlayerTurn(Player.PLAYER_TWO);
            else
                game.setPlayerTurn(Player.PLAYER_ONE);
        }
        log.info("Next player turn set...");
    }

    /**
     * Method to check if last stone finish at any empty pit of same player
     * Takes all the stomes from opponent's pit and put them in his Kalah House
     * Also takes his single stone from his pit and put it into his Kalah House
     *
     * @param game current game
     * @param pitId last pit id of the move
     */

    private void applyMoveFinishingOwnEmptyPitRule(Game game, int pitId) {
        final Pit lastFilledPit = game.getGameBoard().getPit(pitId);
        if (!lastFilledPit.isKalahHouse()) {
            if (lastFilledPit.getOwner().equals(game.getPlayerTurn())) {
                if ((lastFilledPit.getNoOfStones() == 1)) {
                    final Pit complemetaryPit = game.getGameBoard().getPit(Constants.LAST_PIT_ID - lastFilledPit.getId());
                    if (complemetaryPit.getNoOfStones() > 0) {
                        int pitOwnerHouseIndex = lastFilledPit.getOwner().equals(Player.PLAYER_ONE) ? Constants.PLAYER1_KALAH_HOUSE_PIT_ID : Constants.PLAYER2_KALAH_HOUSE_PIT_ID;
                        final Pit kahalHousePit = game.getGameBoard().getPit(pitOwnerHouseIndex);
                        kahalHousePit.setNoOfStones(
                                (kahalHousePit.getNoOfStones() + complemetaryPit.getNoOfStones()) + lastFilledPit.getNoOfStones());
                        complemetaryPit.setNoOfStones(0);
                        lastFilledPit.setNoOfStones(0);
                    }
                }
            }
        }
    }

    /**
     * Method to check after current move if game is finished or not
     * if finished then set the winner
     *
     * @param game current Game
     */

    public void applyGameFinishingRule(Game game) {
        log.info("Checking if game is finished after current move...");
        int playerOnePitStones = game.getGameBoard().getPlayerPitsStones(Player.PLAYER_ONE);
        int playerTwoPitStones = game.getGameBoard().getPlayerPitsStones(Player.PLAYER_TWO);

        if ((playerOnePitStones == 0) || (playerTwoPitStones == 0)) {
            Pit playerOneKalahPit = game.getGameBoard().getPit(Constants.PLAYER1_KALAH_HOUSE_PIT_ID);
            playerOneKalahPit.setNoOfStones(playerOneKalahPit.getNoOfStones()+playerOnePitStones);

            Pit playerTwoKalahPit = game.getGameBoard().getPit(Constants.PLAYER2_KALAH_HOUSE_PIT_ID);
            playerTwoKalahPit.setNoOfStones(playerTwoKalahPit.getNoOfStones()+playerTwoPitStones);

            log.info("Setting winner of the game...");
            if (playerOneKalahPit.getNoOfStones() > playerTwoKalahPit.getNoOfStones())
                game.setWinner(Player.PLAYER_ONE);
            else
                game.setWinner(Player.PLAYER_TWO);
        }
    }

    /**
     * Method to check rule if move requestd by player is a valid one
     *
     *@param game current Game
     *@param pitId  pit id selected by the player
     *@return true or false
     */
    public boolean isValiddMove(Game game, Integer pitId){
        log.debug("Making move for Game - {} by Player - {} with pit - {} ",game.getId(),game.getPlayerTurn(),pitId);
        Pit pit = game.getGameBoard().getPit(pitId);
        log.info("Checking if its a valid move.");

        if(pit.isKalahHouse()){
            throw new InvalidMoveException("ERR_KALAH_HOUSE_MOVE","Cannot move stone from Kalah House Pit");
        }
        if(pit.getOwner()!=game.getPlayerTurn()){
            throw new InvalidMoveException("ERR_KALAH_OPPONENT_PIT_MOVE","Cannot move stone from opponent's Pit");
        }
        if(pit.getNoOfStones()==0){
            throw new InvalidMoveException("ERR_KALAH_EMPTY_PIT_MOVE","Cannot move stone from empty Pit");
        }
        return true;
    }
}
