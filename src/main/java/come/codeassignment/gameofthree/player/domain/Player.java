package come.codeassignment.gameofthree.player.domain;

import come.codeassignment.gameofthree.gameRound.OutputNumberMem;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;

public abstract class Player {
    /**
     * Register the player
     */
    public abstract void register();

    /**
     * Receive the input game round number
     * @param number
     * @return
     */
    public abstract OutputNumberMem receive(InputGameRound number);
    public abstract String getName();
}
