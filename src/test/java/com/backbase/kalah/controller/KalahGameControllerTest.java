package com.backbase.kalah.controller;

import com.backbase.kalah.model.Game;
import com.backbase.kalah.service.KalahService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
class KalahGameControllerTest {


   @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private KalahService kalahService;

    private MockMvc mockMvc;

    @PostConstruct
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testCreateGame() throws Exception {
        MockHttpServletRequestBuilder mockKalahNewGameRequest = MockMvcRequestBuilders.post("/games");
        mockMvc.perform(mockKalahNewGameRequest).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.uri").isNotEmpty()).andReturn();
    }

    @Test
    public void testMakeMove() throws Exception {
        Game game = kalahService.createGame();
        MockHttpServletRequestBuilder playGameRequest =
                MockMvcRequestBuilders.put("/games/" + game.getId() + "/pits/1");
        mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(game.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.uri").value(Matchers.endsWith(game.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.1").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.2").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.3").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.4").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.5").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.6").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.7").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.8").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.9").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.10").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.11").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.12").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.13").value("6"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status.14").value("0"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.playerTurn").value("PLAYER_ONE")).andReturn();
    }

    @Test
    public void testGameNotFoundException() throws Exception {
        String gameId = "5fcdfefcac653a63c660bd01";
        MockHttpServletRequestBuilder playGameRequest =
                MockMvcRequestBuilders.put("/games/"+gameId+"/pits/1");
        mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("ERR_GAME_NOT_FOUND"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Game "+gameId+" not found."))
                .andReturn();

    }

    @Test
    public void testInvalidMoveExceptionWithOpponentPit() throws Exception {
        Game game = kalahService.createGame();
        MockHttpServletRequestBuilder playGameRequest =
                MockMvcRequestBuilders.put("/games/"+game.getId()+"/pits/8");
        mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("ERR_KALAH_OPPONENT_PIT_MOVE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Cannot move stone from opponent's Pit"))
                .andReturn();

    }

    @Test
    public void testInvalidMoveExceptionWithKalahHouse() throws Exception {
        Game game = kalahService.createGame();
        MockHttpServletRequestBuilder playGameRequest =
                MockMvcRequestBuilders.put("/games/"+game.getId()+"/pits/7");
        mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("ERR_KALAH_HOUSE_MOVE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Cannot move stone from Kalah House Pit"))
                .andReturn();

    }

    @Test
    public void testInvalidMoveExceptionWithEmptyPit() throws Exception {
        Game game = kalahService.createGame();
        game  =  kalahService.makeMove(game.getId(),1);
        MockHttpServletRequestBuilder playGameRequest =
                MockMvcRequestBuilders.put("/games/"+game.getId()+"/pits/1");
        mockMvc.perform(playGameRequest).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("ERR_KALAH_EMPTY_PIT_MOVE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Cannot move stone from empty Pit"))
                .andReturn();

    }

}
