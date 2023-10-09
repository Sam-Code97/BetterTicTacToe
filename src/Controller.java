import java.util.Objects;

public class Controller {

    private final RuleEngine ruleEngine;
    private final View view;
    private final Board board;
    public GameType gameType;
    static boolean gameStarted;


    Controller(RuleEngine _ruleEngine){
        ruleEngine = _ruleEngine;
        gameStarted = false;
        board = new Board(this);
        board.initializeBoard(1, 1);
        view = new View(this);
    }


    public String[][] getBoard(){
        return Board.board;
    }

    public boolean unitTaken(int r, int c){
        String[][] _board = getBoard();
        for(int i=0; i<ruleEngine.playerSymbol().size(); i++) {
            System.out.println("r: " + r + ", c: " + c + ", _board.length: " + _board.length );
            if (Objects.equals(_board[r][c], ruleEngine.playerSymbol().get(i))) {
                return true;
            }
        }
        return false;
    }

    public void unitClicked(int _r,int _c){
        if(!gameStarted){
            gameType = gameChoice(_r);
            ruleEngine.choseGame(gameType);
            gameStarted = true;
            view.viewFrame(ruleEngine.getBoardRows(), ruleEngine.getBoardCols());

        }
        else{
            String symbol = ruleEngine.playerSymbol().get(ruleEngine.getPlayerTurn());

            if(!ruleEngine.isGameover() && !unitTaken(_r, _c)) {
                board.updateBoard(_r, _c, symbol);
                ruleEngine.updatePlayerTurn();
                int winner = ruleEngine.getWinner(board.getBoard()) + 1;
                if (winner > 0) {
                    view.updateViewLable("Player " + winner + " is the winner");
                }
                else view.updateViewLable("Player " + (ruleEngine.getPlayerTurn() + 1) + "'s turn");
                view.updateViewBoard(_r, _c, symbol);
            }
        }
    }

    public GameType gameChoice(int index){
        return GameType.values()[index];
    }

    public int getRows(){
        return board.rows;
    }

    public int getCols(){
        return board.cols;
    }

    public void createBoard(int r, int c){
        board.initializeBoard(r, c);
    }




}
