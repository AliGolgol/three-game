package come.codeassignment.gameofthree.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
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
@TestPropertySource("/application.properties")
public class AcceptanceTest {

    MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    GameController gameController;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.gameController).build();// Standalone context
    }
    @Test

    public void should_determineTheWinner_when_thereIsAPossibleOne() throws Exception {
        String createUri = String.format("/api/v1/create?playerType=%s", 1);
        String playUri = "/api/v1/play";
        InputGameRound inputGameRound = new InputGameRound(-1,10);

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

        inputGameRound = new InputGameRound(0,3);
        json = writer.writeValueAsString(inputGameRound);
        MvcResult result = mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertTrue("The winner is PLAYER 2",result.getResponse().getContentAsString().contains("The winner is PLAYER 2"));
    }
}