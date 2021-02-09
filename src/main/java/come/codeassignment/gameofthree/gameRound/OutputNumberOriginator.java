package come.codeassignment.gameofthree.gameRound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutputNumberOriginator {
    private final Logger LOGGER = LoggerFactory.getLogger(OutputNumberMem.class);

    private int addition;
    private int sum;
    private String playerName;
    private boolean winner;

    /**
     * Initialize output number
     * @param addition
     * @param sum
     * @param winner
     * @param playerName
     * @return
     */
    public OutputNumberMem save(int addition, int sum, boolean winner, String playerName) {
        this.addition = addition;
        this.sum = sum;
        this.winner = winner;
        this.playerName = playerName;
        return new OutputNumberMem(addition, sum, winner, playerName);
    }

    /**
     * Restore output number
     * @param save
     */
    public void restore(OutputNumberMem save) {
        if (save != null) {
            this.addition = save.addition;
            this.sum = save.result;
            this.winner = save.isWinner;
            this.playerName = save.playerName;
        } else {
            LOGGER.info("Can not restore this");
        }
    }
}
