package gameRound.validator;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;
import come.codeassignment.gameofthree.gameRound.validator.InputRangeValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.env.Environment;

import static org.junit.Assert.assertEquals;

public class InputDivideByThreeValidatorTest  {
    InputRangeValidator validator;
    Environment environment;

    static {
        PropertiesConfig.initialize("application.properties");
    }

    @Before
    public void setup() {
        validator = new InputRangeValidator();
    }

    @Test
    public void additionShouldBeInValidRange() {
        boolean result = validator.validate(new InputGameRound(-1, 9));
        assertEquals("Addition value should be in [-1, 0, 1] range", true, result);
    }

    @Test
    public void returnFalseIfAdditionIsNotInRange() {
        boolean result = validator.validate(new InputGameRound(2, 9));
        assertEquals("Addition value should be in [-1, 0, 1] range", false, result);
    }
}