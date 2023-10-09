import java.util.HashMap;
import java.util.Stack;

public class TicTacToeRules implements GameRules{

    private int rows = 6;
    private int cols = 3;
    private HashMap<Integer, String> playerSymbol;
    private int players;
    private int playerTurn;
    private boolean isGameOver;


    public TicTacToeRules() {
        initializeGame();
    }
    @Override
    public void initializeGame() {

        playerSymbol = new HashMap<>();
        playerSymbol.put(0, "O");
        playerSymbol.put(1, "X");

        players = 2;
        playerTurn = 0;
        isGameOver = false;
    }
    @Override
    public void updatePlayerTurn(){
        playerTurn = (playerTurn + 1) % players;
    }


    /**
     * Checks the game board for a winning condition.
     * @param board The current game board.
     * @return The index of the winning player or -1 if no winner.
     */
    @Override
    public int getWinner(String[][] board1) {
        int winner = -1;

        for (int player : playerSymbol.keySet()) {
            String symbol = playerSymbol.get(player);

            // Check verticals and horizontals
            for (int r = 0; r < rows; r++) {
                boolean verticalWin = true;
                boolean horizontalWin = true;
                //int temp = rows;
                //rows = cols;
                //cols = temp;
                for (int c = 0; c < cols; c++) {
                    if (board1[r][c] == null || !board1[r][c].equals(symbol)) {
                        verticalWin = false;
                    }
                    if (board1[c][r] == null || !board1[c][r].equals(symbol)) {
                        horizontalWin = false;
                    }
                }
                if (verticalWin || horizontalWin) {
                    winner = player;
                    break;
                }
            }

            // Check diagonals
            boolean leftDiagonalWin = true;
            boolean rightDiagonalWin = true;
            for (int i = 0; i < rows; i++) {
                if (board1[i][i] == null || !board1[i][i].equals(symbol)) {
                    leftDiagonalWin = false;
                }
                if (board1[i][(cols - 1 - i)] == null || !board1[i][cols - 1 - i].equals(symbol)) {
                    rightDiagonalWin = false;
                }
            }
            if (leftDiagonalWin || rightDiagonalWin) {
                winner = player;
                break;
            }
        }

        // If no winner, check for draw
        if (winner == -1 && isDraw(board1)) {
            winner = 3; // Assuming 3 indicates a draw
        }

        if (winner >= 0) isGameOver = true;
        return winner;
    }

    public boolean isDraw(String[][] board1){
        boolean isDraw = true;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board1[r][c] == null) { // Assuming empty cells are null or empty strings
                    isDraw = false;
                    break;
                }
            }
            if (!isDraw) break;
        }
        return isDraw;
    }

    // Getters and setters for the member variables
    public int getBoardRows(){
        return rows;
    }

    public int getBoardCols(){
        return cols;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public HashMap<Integer, String> getPlayerSymbol(){
        return playerSymbol;
    }
}


