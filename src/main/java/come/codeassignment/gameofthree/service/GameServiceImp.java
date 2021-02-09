package come.codeassignment.gameofthree.service;


import come.codeassignment.gameofthree.gameRound.OutputCareTaker;
import come.codeassignment.gameofthree.gameRound.OutputNumberMem;
import come.codeassignment.gameofthree.gameRound.domain.Game;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImp implements GameService {
    private final Logger logger = LoggerFactory.getLogger(GameServiceImp.class);
    Game game = new Game();
    OutputCareTaker careTaker = new OutputCareTaker();

    /**
     * Create and start a game base on Player type
     * @param playerType to define a Human or Machine player
     * @return the origin number to start
     */
    @Override
    public int playWithPlayerOfType(int playerType) {
        game.withPlayerOfType(playerType).create().start();
        if (game.canPlay()) {
            return game.getStartNumber();
        }
        return 0;
    }

    /**
     * Play with peer that can be a Human or Machine
     * @param number
     * @return OutputDto includes additional number, number, player name and message
     */
    @Override
    public OutputDto play(InputGameRound number) {
//        logger.info(String.valueOf(game.players.size()));
//        logger.info(game.getCurrentPlayer());
        OutputDto result;
        OutputNumberMem outputNumberMem = null;
//        logger.info(String.valueOf(game.canPlay()));
        if (game.canPlay()) {
            outputNumberMem = game.play(number);
            careTaker.add(outputNumberMem);
            result = new OutputDto(outputNumberMem.getAddition(), outputNumberMem.getResult(),
                    "", outputNumberMem.getPlayerName());
        } else {
            result = new OutputDto(number.getAdditionNumber(), number.getNumber(),
                    String.format("%s, you should wait to another player to start", game.getCurrentPlayer()),
                    game.getCurrentPlayer());
        }
        if (outputNumberMem != null && outputNumberMem.isWinner()) {
            result = new OutputDto(outputNumberMem.getAddition(), outputNumberMem.getResult(),
                    String.format("The winner is %s", outputNumberMem.getPlayerName()),
                    outputNumberMem.getPlayerName());
        }
        return result;
    }
}
