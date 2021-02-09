package come.codeassignment.gameofthree.gameRound.validator;


import come.codeassignment.gameofthree.gameRound.domain.InputGameRound;

public interface Validator {

    /**
     * Validate the input game round
     * @param input
     * @return
     */
    boolean validate(InputGameRound input);

    /**
     * Valid or not the input game round
     * @param input
     */
    void validateOrNot(InputGameRound input);
}
