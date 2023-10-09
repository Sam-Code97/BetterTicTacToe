public class Board {

    //Controller _controller
    private int rows;
    private int cols;
    private String[][] board;

    Board(Controller _controller){
    }

    public void initializeBoard(int _rows, int _cols){
        rows = _rows;
        cols = _cols;
        board = new String[rows][cols];
    }

    public void updateBoard(int _r, int _c, String symbol){
        board[_r][_c] = symbol;
    }

    public String[][] getBoard(){
        return board;
    }

    public int getRows(){
        return rows;
    }
    public int getCols(){
        return cols;
    }


}
