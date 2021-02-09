package come.codeassignment.gameofthree.gameRound.validator;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import come.codeassignment.gameofthree.gameRound.exception.GameRoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InputRangeValidator implements Validator {

    private static final List<Integer> INPUT_NUMBERS = new CopyOnWriteArrayList<>();
    private static String INPUT_RANGE = PropertiesConfig.getProperties().getProperty("game.input_range");

    static {
        String[] inputs = INPUT_RANGE
                .split("[/s,.]");
        Arrays.stream(inputs).forEach(input -> {
            INPUT_NUMBERS.add(Integer.parseInt(input));
        });
    }

    /**
     * Validate the input game round
     * @param input
     * @return
     */
    @Override
    public boolean validate(InputGameRound input) {

        String[] inputs = INPUT_RANGE
                .split("[/s,.]");
        Arrays.stream(inputs).forEach(in -> {
            INPUT_NUMBERS.add(Integer.parseInt(in));
        });
        return INPUT_NUMBERS.contains(input.getAdditionNumber());
    }

    /**
     * Valid or not the input game round
     * @param input
     */
    @Override
    public void validateOrNot(InputGameRound input) {
        if (!INPUT_NUMBERS.contains(input.getAdditionNumber())) {
            throw new GameRoundException("The input number should be in (-1, 0, 1) range");
        }
    }
}
