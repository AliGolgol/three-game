package come.codeassignment.gameofthree.gameRound.domain;

public class InputDTO {
    int additionNumber;
    int number;

    /**
     * The input numbers
     * @param additionNumber
     * @param number
     */
    public InputDTO(int additionNumber, int number) {
        this.additionNumber = additionNumber;
        this.number = number;
    }

    public InputDTO() {
        super();
    }

    public int getAdditionNumber() {
        return additionNumber;
    }

    public void setAdditionNumber(int additionNumber) {
        this.additionNumber = additionNumber;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
