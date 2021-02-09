package gameRound;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.domain.Game;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GameTest {
    static {
        PropertiesConfig.initialize("application.properties");
    }
    Game game = null;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void withPlayerOfType_should_playerType_Game_with_defined_playerType() {
        int playerType = 2;
        game.withPlayerOfType(playerType);

        assertEquals("The type 2 is Machine Player", 2, game.getPlayerType());
    }

    @Test
    public void canPlayShouldReturnFalseWhenThereIsJustOnePlayer() {
        game = game.create();
        assertEquals("canPlay method should return False when there are not enough players to play", false, game.canPlay());
    }

    @Test
    public void canPlayShouldReturnTrueWhenThereIsJustOnePlayer() {
        game.create();
        game.create();

        assertEquals("canPlay method should return True when there are enough players to play", true, game.canPlay());
    }

    @Test
    public void currentPlayerShouldReturnZeroWhenItIsTheFirstRound() {
        game.create();
        game.create();

        assertTrue("Current player is 0 when it is the first round of game",game.getCurrentPlayer().contains("PLAYER"));
    }

    @Test
    public void currentPlayerShouldReturnOneWhenItIsSecondRound() {
        game.create();
        game.create();
        game.play(new InputGameRound(-1,10));
        assertTrue("Current player is 0 when it is the first round of game",game.getCurrentPlayer().contains("PLAYER"));

    }


}