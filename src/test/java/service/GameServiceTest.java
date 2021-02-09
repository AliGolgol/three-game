package service;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import come.codeassignment.gameofthree.service.GameService;
import come.codeassignment.gameofthree.service.GameServiceImp;
import come.codeassignment.gameofthree.service.OutputDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.TestPropertySource;

import static org.junit.Assert.assertEquals;

@TestPropertySource("/application.properties")
public class GameServiceTest {
    GameService service;

    static {
        PropertiesConfig.initialize("application.properties");
    }

    @Before
    public void setup() {
        service = new GameServiceImp();
    }

    @Test
    public void shouldCreateAGameAndReturnStartNumber() {
        int result = service.playWithPlayerOfType(2);
        assertEquals("Should create a Game and return StartNumber", 10, result);
    }

    @Test
    public void shouldCreateAGameAndReturnZeroSinceThereIsJustOnePlayer() {
        int result = service.playWithPlayerOfType(1);
        assertEquals("Should create a Game and return 0 as a StartNumber", 0, result);
    }

    @Test
    public void shouldCreateAGameAndSayYouCanNotPlayGameThereIsJustOnePlayer() {
        service.playWithPlayerOfType(1);
        OutputDto output = service.play(new InputGameRound(-1, 10));
        assertEquals("Player should wait to another player to be connected",
                String.format("%s, you should wait to another player to start",output.getPlayerName()), output.getMessage());
    }

    @Test
    public void shouldCreateAGameAndSayYouCanPlayGameThereIsJustOnePlayer() {
        int startNum1 = service.playWithPlayerOfType(1);
        int startNum2 = service.playWithPlayerOfType(1);
        OutputDto output1 = service.play(new InputGameRound(-1, 10));
        OutputDto output2 = service.play(new InputGameRound(0, output1.getNumber()));
        assertEquals("Should return 0 since the game needs two players", 0, startNum1);
        assertEquals("Should return start-number since there are two players", 10, startNum2);
        assertEquals(1, output2.getNumber());
    }

    @Test
    public void shouldPlayTheRoundGameAndReturnTheResult() {
        int startNumber = service.playWithPlayerOfType(2);
        InputGameRound input = new InputGameRound(-1, startNumber);
        OutputDto output = service.play(input);
        assertEquals("Should create a Game and return StartNumber", 3, output.getNumber());
    }

    @Test
    public void shouldReturnTheFirstPlayer() {
        int startNumber = service.playWithPlayerOfType(1);
        OutputDto output = service.play(new InputGameRound(-1, startNumber));
        assertEquals("Should defines a winner", String.format("%s, you should wait to another player to start", output.getPlayerName()), output.getMessage());

    }

    @Test
    public void shouldReturnTheSecondPlayer() {
        service.playWithPlayerOfType(2);
        OutputDto output = service.play(new InputGameRound(-1, 10));
        assertEquals("Should defines a winner", String.format("The winner is %s", output.getPlayerName()), output.getMessage());
    }
}