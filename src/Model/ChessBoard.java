package Model;

import Controller.ChessBoardController;

/**
 * Created by mayalake on 5/1/18.
 */
public class ChessBoard {
    private int[] board = new int[ChessBoardController.CHESSBOARD_SIZE];
    private int fitness;

    public ChessBoard(){
        board = ChessBoardController.createBoard();
        fitness = ChessBoardController.setFitness(board);
    }

    public ChessBoard(int[] board){
        this.board = board;
        fitness = ChessBoardController.setFitness(board);
    }

    public int[] getBoard(){
        return board;
    }

    public void setBoard(int[] board){
        this.board = board;
    }

    public int getFitness(){
        return fitness;
    }
}
