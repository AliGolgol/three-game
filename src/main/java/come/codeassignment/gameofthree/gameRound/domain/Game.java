package come.codeassignment.gameofthree.gameRound.domain;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.OutputNumberMem;
import come.codeassignment.gameofthree.gameRound.exception.GameRoundException;
import come.codeassignment.gameofthree.gameRound.validator.InputRangeValidator;
import come.codeassignment.gameofthree.gameRound.validator.Validator;
import come.codeassignment.gameofthree.gameRound.winLogic.Winner;
import come.codeassignment.gameofthree.gameRound.winLogic.WinnerImp;
import come.codeassignment.gameofthree.player.domain.Human;
import come.codeassignment.gameofthree.player.domain.Machine;
import come.codeassignment.gameofthree.player.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);
    private static final String DIVIDE_NUMBER = PropertiesConfig.getProperties().getProperty("game.divide");
    static final Map<Integer, Player> map = new HashMap<>();
    static final int MINIMUM_PLAYERS = 2;
    static String MAX_RANGE;
    static String MIN_RANGE;
    int currentPlayer = 0;
    final int HUMAN = 1;
    int startNumber;
    int playerType = 1;
    boolean isWinner;
    List<Player> players = new ArrayList<>();
    Validator validator = null;
    Winner winnerLogic;
    String id;

    static {
        map.put(1, new Human());
        map.put(2, new Machine());
        MAX_RANGE = PropertiesConfig.getProperties().getProperty("game.max-range");
        MIN_RANGE = PropertiesConfig.getProperties().getProperty("game.min-range");
    }

    /**
     * Create a game
     * @return
     */
    public Game create() {
        init();
        return this;
    }

    private void init() {
        winnerLogic = new WinnerImp();
        this.id = UUID.randomUUID().toString();
        if (map.get(playerType) instanceof Machine) {
            Player machinePlayer = map.get(HUMAN);
            machinePlayer.register();
            players.add(machinePlayer);

            Player humanPlayer = map.get(playerType);
            humanPlayer.register();
            players.add(humanPlayer);
        } else {
            Player human = new Human();// map.get(HUMAN);
            human.register();
            players.add(human);
        }
    }

    /**
     * start a game
     */
    public void start() {
        startNumber = ThreadLocalRandom.current().nextInt(Integer.parseInt(MIN_RANGE), Integer.parseInt(MAX_RANGE));
    }

    /**
     * Play the game with Input Game Round
     * @param number
     * @return
     */
    public OutputNumberMem play(InputGameRound number) {
//        LOGGER.info(number.toString());
        OutputNumberMem outputNumberMem;
        int inputResult;
        try {
            number.validate();
            inputResult = number.sum() / Integer.parseInt(DIVIDE_NUMBER);
            if (winnerLogic.apply(inputResult)) {
                return new OutputNumberMem(number.getAdditionNumber(), inputResult, true, players.get(currentPlayer).getName());
            }
            InputGameRound input = new InputGameRound(number.additionNumber, number.getNumber());
            outputNumberMem = next().receive(input);

            isWinner = winnerLogic.apply(outputNumberMem.getResult());
            outputNumberMem.defineWinnerStatus(isWinner);
            outputNumberMem.definePlayer(players.get(currentPlayer).getName());
            next();
            return outputNumberMem;
        } catch (GameRoundException e) {
//            LOGGER.info(e.toString());
            throw e;
        }
    }

    /**
     * Defines the Human/Machine player
     * @param playerType
     * @return
     */
    public Game withPlayerOfType(int playerType) {
        this.playerType = playerType;
        validator = new InputRangeValidator();
        return this;
    }

    /**
     * It is a circle array to determine the players' turn
     * @return
     */
    private Player next() {
        currentPlayer = (currentPlayer + 1) % players.size();
        return players.get(currentPlayer);
    }

    /**
     * Get the defined number
     * @return
     */
    public int getStartNumber() {
        return startNumber;
    }

    /**
     * Whether possible to start or continue the game or not
     * @return
     */
    public boolean canPlay() {
        return players.size() >= MINIMUM_PLAYERS;
    }

    /**
     * Who is the current the player
     * @return
     */
    public String getCurrentPlayer() {
        return players.get(currentPlayer).getName();
    }

    /**
     * Get the type of player
     * @return
     */
    public int getPlayerType() {
        return playerType;
    }
}