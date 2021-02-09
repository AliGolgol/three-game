package come.codeassignment.gameofthree.gameRound.domain;


import come.codeassignment.gameofthree.gameRound.validator.InputDivideByThreeValidator;
import come.codeassignment.gameofthree.gameRound.validator.Validator;

import java.util.Objects;

public class InputGameRound {
    int additionNumber;
    int number;

    /**
     * The input game round value object
     */
    public InputGameRound() {
        sum();
    }

    /**
     * The input game round value object
     * @param additionNumber
     * @param number
     */
    public InputGameRound(int additionNumber, int number) {
        this.additionNumber = additionNumber;
        this.number = number;
    }

    /**
     * Get the addition number
     * @return
     */
    public int getAdditionNumber() {
        return additionNumber;
    }

    /**
     * Get the number
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * Sum of addition number and number
     * @return
     */
    public int sum() {
        return additionNumber + number;
    }

    /**
     * Validate the input number
     */
    public void validate(){
        Validator validator = new InputDivideByThreeValidator();
        validator.validateOrNot(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputGameRound that = (InputGameRound) o;
        return additionNumber == that.additionNumber && number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(additionNumber, number);
    }

    @Override
    public String toString() {
        return "InputGameRound{" +
                "additionNumber=" + additionNumber +
                ", number=" + number +
                '}';
    }
}

