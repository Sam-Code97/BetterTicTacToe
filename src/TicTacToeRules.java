import java.util.HashMap;

public class TicTacToeRules implements GameRules{

    private static final int ROWS = 6;
    private static final int COLS = 3;
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
    public int getWinner(String[][] board, int clickedRow, int clickedCol) {
        int winner = -1; // indicates no winner yet

        for (int player : playerSymbol.keySet()) { // loops through the chosen symbols
            String symbol = playerSymbol.get(player);

            // Check horizontal
            for (int c = Math.max(0, clickedCol - 2); c <= Math.min(COLS - 3, clickedCol); c++) {
                if (board[clickedRow][c] == symbol && board[clickedRow][c + 1] == symbol && board[clickedRow][c + 2] == symbol) {
                    winner = player;
                    isGameOver = true;
                    break;
                }
            }

            // Check vertical
            for (int r = Math.max(0, clickedRow - 2); r <= Math.min(ROWS - 3, clickedRow); r++) {
                if (board[r][clickedCol] == symbol && board[r + 1][clickedCol] == symbol && board[r + 2][clickedCol] == symbol) {
                    winner = player;
                    isGameOver = true;
                    break;
                }
            }
            // Check diagonals (only for 3x3 board)
            if (ROWS == 3 && COLS == 3) {
                if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol ||
                        board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
                    winner = player;
                    isGameOver = true;
                    break;
                }
            }
            if (isGameOver) break;
        }
        // Check for draw
        if (winner == -1 && isDraw(board)) {
            winner = 3; // 3 indicates a draw
        }
        return winner;
    }

    public boolean isDraw(String[][] board1){
        boolean isDraw = true;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                // if at least one cell is empty. This is no draw
                if (board1[r][c] == null) {
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
        return ROWS;
    }

    public int getBoardCols(){
        return COLS;
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


