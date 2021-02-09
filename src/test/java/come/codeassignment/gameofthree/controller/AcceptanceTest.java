package come.codeassignment.gameofthree.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import come.codeassignment.gameofthree.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
@RunWith(SpringRunner.class)
//@TestPropertySource("/application.properties")
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
@ExtendWith(SpringExtension.class)
public class AcceptanceTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    GameController gameController;

    @Autowired
    GameService gameService;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.gameController).build();// Standalone context
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_determineTheWinner_when_thereIsAPossibleOne() throws Exception {
        String createUri = String.format("/api/v1/create?playerType=%s", 1);
        String playUri = "/api/v1/play";
        InputGameRound inputGameRound = new InputGameRound(-1, 10);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(inputGameRound);

        mockMvc.perform(get(createUri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("0"));

        Thread.sleep(3000);
        mockMvc.perform(get(createUri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("10"));

        mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        inputGameRound = new InputGameRound(0, 3);
        json = writer.writeValueAsString(inputGameRound);
        MvcResult result = mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertTrue("The winner is PLAYER ", result.getResponse().getContentAsString().contains("The winner is PLAYER "));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_returnMessageThatYouNeedToWaitToAnother_when_thereIsNotEnoughPlayersToPlay() throws Exception {

        String createUri = String.format("/api/v1/create?playerType=%s", 1);
        String playUri = "/api/v1/play";
        InputGameRound inputGameRound = new InputGameRound(-1, 10);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(inputGameRound);

        mockMvc.perform(get(createUri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("0"));

        MvcResult result = mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertTrue("You should wait to another player to start",
                result.getResponse().getContentAsString().contains("you should wait to another player to start"));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_returnMessageYourNumberIsNotDividable_when_theInputNumberIsNotDividableByThree() throws Exception {
        String createUri = String.format("/api/v1/create?playerType=%s", 1);
        String playUri = "/api/v1/play";
        InputGameRound inputGameRound = new InputGameRound(-1, 10);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(inputGameRound);

        mockMvc.perform(get(createUri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("0"));

        Thread.sleep(3000);
        mockMvc.perform(get(createUri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("10"));

        mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        inputGameRound = new InputGameRound(0, 4);
        json = writer.writeValueAsString(inputGameRound);
        MvcResult result = mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertTrue("The 4 is not dividable by three", result.getResponse().getContentAsString().contains("The 4 is not dividable by three"));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void should_beAbleToPlay_when_theCompetitorIsMachine() throws Exception {
        String createUri = String.format("/api/v1/create?playerType=%s", 2);
        String playUri = "/api/v1/play";
        InputGameRound inputGameRound = new InputGameRound(-1, 10);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(inputGameRound);

        mockMvc.perform(get(createUri).contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("10"));

        mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        inputGameRound = new InputGameRound(0, 3);
        json = writer.writeValueAsString(inputGameRound);
        MvcResult result = mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertTrue("The winner is PLAYER 2", result.getResponse().getContentAsString().contains("The winner is PLAYER 2"));
    }

}