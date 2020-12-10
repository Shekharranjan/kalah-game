package com.backbase.kalah.rule;

import com.backbase.kalah.model.Game;
import com.backbase.kalah.model.Player;
import com.backbase.kalah.service.KalahService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class KalahGameRuleRunnerTest {

    @Autowired
    KalahGameRule kalahGameRuleRunner;

    @Autowired
    KalahService kalahService;


    @Test
    void testPlayerTurn() {
        Game game = kalahService.createGame();
        kalahGameRuleRunner.applyGameRule(game, 1);
        Assert.assertNotNull(game);
        Assert.assertEquals(game.getPlayerTurn(), Player.PLAYER_ONE);
    }

    @Test
    void testIfLastStoneEndsAtOwnKalahHouse() {
        Game game = kalahService.createGame();
        kalahGameRuleRunner.applyGameRule(game, 1);
        Assert.assertEquals(game.getPlayerTurn(), Player.PLAYER_ONE);
    }

}