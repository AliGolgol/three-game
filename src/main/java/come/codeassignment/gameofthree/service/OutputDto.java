package come.codeassignment.gameofthree.service;

public class OutputDto {
    int addition;
    int number;
    String message;
    String playerName;

    /**
     * Data transfer object to send to players
     * @param addition
     * @param number
     * @param message
     * @param playerName
     */
    public OutputDto(int addition, int number, String message, String playerName) {
        this.addition = addition;
        this.number = number;
        this.message = message;
        this.playerName = playerName;
    }

    public int getAddition() {
        return addition;
    }

    public int getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }
    public String getPlayerName(){return playerName;}
}
