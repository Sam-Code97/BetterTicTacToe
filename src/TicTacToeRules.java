import java.util.HashMap;
import java.util.Stack;

public class TicTacToeRules implements GameRules{

    private int rows;
    private int cols;
    private HashMap<Integer, String> playerSymbol;
    private int players;
    private int playerTurn;
    private boolean isGameOver;


    public String[][] board;


    public TicTacToeRules() {
        initializeGame();
    }
    @Override
    public void initializeGame() {
        rows = 3;
        cols = 3;

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
    @Override
    public int getWinner(String[][] board1){
        int winner = -1;

        for(int player : playerSymbol.keySet()) {
            String symbol = playerSymbol.get(player);

            for (int i = 0; i < 3; i++) {
                if (board1[i][0] == symbol && board1[i][1] == symbol && board1[i][2] == symbol ||   // check verticals
                        board1[0][i] == symbol && board1[1][i] == symbol && board1[2][i] == symbol)     // check horizontal
                    winner = player;
            }
            if (board1[0][0] == symbol && board1[1][1] == symbol && board1[2][2] == symbol ||       // check diagonal
                    board1[0][2] == symbol && board1[1][1] == symbol && board1[2][0] == symbol)
                winner = player;
        }
        if(winner >= 0) isGameOver = true;
        return winner;
    };


    // Getters and setters for the member variables
    public int getBoardRows(){
        return rows;
    }

    public int getBoardCols(){
        return cols;
    }
    public int getPlayers() {
        return players;
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


