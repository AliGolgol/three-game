package come.codeassignment.gameofthree.gameRound;

public class OutputNumberMem {
    int addition;
    int result;
    boolean isWinner;
    String playerName;

    /**
     * Initial the output number
     * @param addition
     * @param result
     * @param isWinner
     * @param playerName
     */
    public OutputNumberMem(int addition, int result, boolean isWinner, String playerName) {
        this.addition = addition;
        this.result = result;
        this.isWinner = isWinner;
        this.playerName = playerName;
    }

    /**
     * Define the winner status
     * @param isWinner
     */
    public void defineWinnerStatus(boolean isWinner) {
        this.isWinner = isWinner;
    }

    /**
     * Define the player name
     * @param playerName
     */
    public void definePlayer(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Get addition number
     * @return
     */
    public int getAddition() {
        return addition;
    }

    /**
     * Get the result
     * @return
     */
    public int getResult() {
        return result;
    }

    /**
     * Is winner or not
     * @return
     */
    public boolean isWinner() {
        return isWinner;
    }

    /**
     * Get player's name
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public String toString() {
        return "OutputNumber{" +
                "addition=" + addition +
                ", result=" + result +
                '}';
    }
}
