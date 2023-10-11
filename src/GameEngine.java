import java.util.HashMap;

public class GameEngine {
    private final Controller controller;
    private GameRules currentGameRules;

    GameEngine(){
        controller = new Controller(this);
    }

    public void choseGame(GameType gameType) {
        switch(gameType) {
            case TIC_TAC_TOE:
                currentGameRules = new TicTacToeRules();
                createBoard(currentGameRules.getBoardRows(), currentGameRules.getBoardCols());/////
                break;
            case CONNECT_FOUR:
                 currentGameRules = new ConnectFourRules();
                createBoard(currentGameRules.getBoardRows(), currentGameRules.getBoardCols());/////
                 break;
            // case CHESS:  // here, I can add more games
            //     currentGameRules = new ChessRules();
            //     break;
            default:
                throw new IllegalArgumentException("Invalid game type");
        }
        currentGameRules.initializeGame();

    }

    public void createBoard(int r, int c){
        controller.createBoard(r, c);
    }

    public int getWinner(String[][] board, int clickedRow, int clickedCol){
        return currentGameRules.getWinner(board, clickedRow, clickedCol);
    }

    public int getPlayerTurn(){
        return currentGameRules.getPlayerTurn();
    }

    public void updatePlayerTurn(){
        currentGameRules.updatePlayerTurn();
    }

    public boolean isGameOver() {
        return currentGameRules.isGameOver();
    }

    public int getBoardRows(){
        return currentGameRules.getBoardRows();
    }

    public int getBoardCols(){
        return currentGameRules.getBoardCols();
    }

    public HashMap<Integer, String> playerSymbol(){
        return currentGameRules.getPlayerSymbol();
    }


}
