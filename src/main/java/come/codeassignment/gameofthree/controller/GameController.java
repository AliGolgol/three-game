package come.codeassignment.gameofthree.controller;

import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import come.codeassignment.gameofthree.gameRound.exception.GameRoundException;
import come.codeassignment.gameofthree.service.GameService;
import come.codeassignment.gameofthree.service.OutputDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class GameController {
    Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private GameService gameService;

    /**
     * Create and start a game
     * @param playerType to define a Human or Machine player
     * @return the origin number to start
     */
    @GetMapping("/create")
    public int create(@RequestParam(name="playerType") int playerType) {
        try {
            return gameService.playWithPlayerOfType(playerType);
        }catch (Exception e){
            LOGGER.error(e.toString());
        }
        return 0;
    }

    /**
     * Play with peer that can be a Human or Machine
     * @param number
     * @return OutputDto includes additional number, number, player name and message
     */
    @PostMapping(value = "/play")
    public OutputDto play(@RequestBody InputGameRound number) {
        InputGameRound input = new InputGameRound(number.getAdditionNumber(),number.getNumber());
        OutputDto result = null;
        try {
            result = gameService.play(number);
        } catch (GameRoundException e) {
            result = new OutputDto(number.getAdditionNumber(), number.getNumber(), e.getMessage(), "");
        }
        return result;
    }
}
