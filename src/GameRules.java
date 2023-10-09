import java.util.HashMap;

public interface GameRules {
    void initializeGame();
    int getBoardRows();
    int getBoardCols();
    int getWinner(String[][] board);
    void updatePlayerTurn();
    boolean isGameOver();
    int getPlayerTurn();
    HashMap<Integer, String> getPlayerSymbol();

}