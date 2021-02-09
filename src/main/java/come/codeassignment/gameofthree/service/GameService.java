package come.codeassignment.gameofthree.service;

import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;

public interface GameService {

    /**
     * Create and start a game base on Player type
     * @param playerType to define a Human or Machine player
     * @return the origin number to start
     */
    int playWithPlayerOfType(int playerType);

    /**
     * Play with peer that can be a Human or Machine
     * @param number
     * @return OutputDto includes additional number, number, player name and message
     */
    OutputDto play(InputGameRound number);
}
