package come.codeassignment.gameofthree.player.domain;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.OutputNumberMem;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Machine extends Player {

    private static final Map<Integer, Integer> ADDITION_NUMBERS;
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private static final String DIVIDE_NUMBER = PropertiesConfig.getProperties().getProperty("game.divide");
    private String name;
    String id;

    static {
        ADDITION_NUMBERS = new HashMap<>();
        ADDITION_NUMBERS.put(0, 0);
        ADDITION_NUMBERS.put(1, -1);
        ADDITION_NUMBERS.put(2, 1);
    }

    /**
     * Register a Machine player
     */
    public void register() {
        id = UUID.randomUUID().toString();
        name = "PLAYER 2" ;
    }

    /**
     * Receive a input game round number to play
     * @param number
     * @return
     */
    public OutputNumberMem receive(InputGameRound number) {
        int remain = number.getNumber() % Integer.parseInt(DIVIDE_NUMBER);
        int addition = ADDITION_NUMBERS.get(remain);
        int result = (number.getNumber() + addition) / Integer.parseInt(DIVIDE_NUMBER);
        return new OutputNumberMem(addition, result, false, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
