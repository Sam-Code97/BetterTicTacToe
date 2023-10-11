import java.util.Objects;

public class Controller {

    private final GameEngine gameEngine;
    private final View view;
    private final Board board;
    public GameType gameType;
    static boolean gameStarted;


    Controller(GameEngine _gameEngine){
        gameEngine = _gameEngine;
        gameStarted = false;
        board = new Board(this);
        board.initializeBoard(1, 1);
        view = new View(this);
    }


    public String[][] getBoard(){
        return board.getBoard();
    }

    public boolean unitTaken(int r, int c){
        String[][] _board = getBoard();
        for(int i = 0; i< gameEngine.playerSymbol().size(); i++) {
            System.out.println("r: " + r + ", c: " + c + ", _board.length: " + _board.length );
            if (Objects.equals(_board[r][c], gameEngine.playerSymbol().get(i))) {
                return true;
            }
        }
        return false;
    }

    public void unitClicked(int _r,int _c){
        if(!gameStarted){
            gameType = gameChoice(_r);
            gameEngine.choseGame(gameType);
            gameStarted = true;
            view.viewFrame(gameEngine.getBoardRows(), gameEngine.getBoardCols());

        }
        else{
            String symbol = gameEngine.playerSymbol().get(gameEngine.getPlayerTurn());

            if(!gameEngine.isGameOver() && !unitTaken(_r, _c)) {
                board.updateBoard(_r, _c, symbol);
                gameEngine.updatePlayerTurn();
                int winner = gameEngine.getWinner(board.getBoard(), _r, _c) + 1 ;
                if ( winner > 0) {
                    if(winner > 2) { // Draw
                        view.updateViewLable("Draw");
                    }
                    else view.updateViewLable("Player " + winner + " is the winner");
                }
                else view.updateViewLable("Player " + (gameEngine.getPlayerTurn() + 1) + "'s turn");
                view.updateViewBoard(_r, _c, symbol);
            }
        }
    }

    public GameType gameChoice(int index){
        return GameType.values()[index];
    }

    public int getRows(){
        return board.getRows();
    }

    public int getCols(){
        return board.getCols();
    }

    public void createBoard(int r, int c){
        board.initializeBoard(r, c);
    }




}
