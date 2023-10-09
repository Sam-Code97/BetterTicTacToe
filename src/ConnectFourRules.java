import java.util.HashMap;
import java.util.Map;

public class ConnectFourRules implements GameRules {

    private static final int ROWS = 6;
    private static final int COLS = 7;
    private HashMap<Integer, String> playerSymbol;
    private int players;
    private int playerTurn;
    private boolean isGameOver;
    private boolean islegalMove;

    public ConnectFourRules() {
        initializeGame();
    }

    @Override
    public void initializeGame() {
        playerSymbol = new HashMap<>();
        playerSymbol.put(0, "R"); // Red
        playerSymbol.put(1, "Y"); // Yellow

        players = 2;
        playerTurn = 0;
        isGameOver = false;
        islegalMove = false;
    }

    @Override
    public int getWinner(String[][] board) {
        // Check for 4 in a row, column, or diagonal
        int winner = -1;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                String current = board[r][c];
                if (current == null) continue;

                // Check right
                if (c + 3 < COLS &&
                        current.equals(board[r][c + 1]) &&
                        current.equals(board[r][c + 2]) &&
                        current.equals(board[r][c + 3])) {
                    winner = playerSymbol.entrySet().stream()
                            .filter(entry -> current.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst().orElse(-1);
                }

                // Check down
                if (r + 3 < ROWS &&
                        current.equals(board[r + 1][c]) &&
                        current.equals(board[r + 2][c]) &&
                        current.equals(board[r + 3][c])) {
                    winner =playerSymbol.entrySet().stream()
                            .filter(entry -> current.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst().orElse(-1);
                }

                // Check diagonal down-right
                if (r + 3 < ROWS && c + 3 < COLS &&
                        current.equals(board[r + 1][c + 1]) &&
                        current.equals(board[r + 2][c + 2]) &&
                        current.equals(board[r + 3][c + 3])) {
                    winner =playerSymbol.entrySet().stream()
                            .filter(entry -> current.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst().orElse(-1);
                }

                // Check diagonal down-left
                if (r + 3 < ROWS && c - 3 >= 0 &&
                        current.equals(board[r + 1][c - 1]) &&
                        current.equals(board[r + 2][c - 2]) &&
                        current.equals(board[r + 3][c - 3])) {
                    winner = playerSymbol.entrySet().stream()
                            .filter(entry -> current.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst().orElse(-1);
                }
            }
        }
        if(winner >= 0) isGameOver = true;
        return winner;
    }

    @Override
    public void updatePlayerTurn() {
        playerTurn = (playerTurn + 1) % players;
    }

    @Override
    public int getBoardRows() {
        return ROWS;
    }

    @Override
    public int getBoardCols() {
        return COLS;
    }

    @Override
    public boolean isGameOver() {
        return isGameOver;
    }

    @Override
    public int getPlayerTurn() {
        return playerTurn;
    }

    @Override
    public HashMap<Integer, String> getPlayerSymbol() {
        return playerSymbol;
    }
}
