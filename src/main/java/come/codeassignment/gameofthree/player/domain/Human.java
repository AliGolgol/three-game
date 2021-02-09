package come.codeassignment.gameofthree.player.domain;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.OutputNumberMem;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Human extends Player {
    private String id;
    private String name;
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
    private static final String DIVIDE_NUMBER = PropertiesConfig.getProperties().getProperty("game.divide");

    /**
     * Register a Human player
     */
    public void register() {
        id = UUID.randomUUID().toString();
        name = "PLAYER " + ATOMIC_INTEGER.incrementAndGet();
    }

    /**
     * Receive the input game round number
     * @param number
     * @return
     */
    public OutputNumberMem receive(InputGameRound number) {
        int result = number.sum() / Integer.parseInt(DIVIDE_NUMBER);
        return new OutputNumberMem(number.getAdditionNumber(), result, false, name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
