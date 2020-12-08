package com.backbase.kalah.service;

import com.backbase.kalah.model.Game;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class KalahGameServiceTest {


    @Autowired
    KalahService kalahService;

    @Test
    void testNewGameCreation() {
        Game game = kalahService.createGame();
        Assert.assertNotNull(game );
    }

}