package come.codeassignment.gameofthree.gameRound.validator;


import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import come.codeassignment.gameofthree.gameRound.exception.GameRoundException;

public class InputDivideByThreeValidator implements Validator {
    private static final int DIVIDE;

    static {
        DIVIDE = Integer.parseInt(PropertiesConfig.getProperties().getProperty("game.divide"));
    }

    /**
     * Validate the input game round
     * @param input
     * @return
     */
    @Override
    public boolean validate(InputGameRound input) {
        int sum = input.sum();
        int result = sum % DIVIDE;
        return result == 0;
    }

    /**
     * Valid or not the input game round
     * @param input
     */
    @Override
    public void validateOrNot(InputGameRound input) {
        int sum = input.sum();
        if (sum % DIVIDE != 0) {
            throw new GameRoundException(String.format("The %s is not dividable by three", input.getNumber()));
        }
    }
}
