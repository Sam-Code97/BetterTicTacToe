import java.util.HashMap;

public interface GameRules {
    void initializeGame();
    int getBoardRows();
    int getBoardCols();
    int getWinner(String[][] board);
    void updatePlayerTurn();

    int getPlayers();
    boolean isGameOver();
    int getPlayerTurn();
    public HashMap<Integer, String> playerSymbol = null;
    HashMap<Integer, String> getPlayerSymbol();

}