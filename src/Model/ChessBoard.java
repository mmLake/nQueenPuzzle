package Model;

import Controller.ChessBoardController;

import java.util.Comparator;

/**
 * Created by mayalake on 5/1/18.
 */
public class ChessBoard implements Comparable<ChessBoard>{
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

    public ChessBoard(ChessBoard chessBoard){
        this.board = chessBoard.getBoard();
        this.fitness = chessBoard.getFitness();
    }

    public int[] getBoard(){
        return board;
    }

    public void setBoard(int[] board){
        this.board = board;
        fitness = ChessBoardController.setFitness(board);
    }

    public void setChessBoard(ChessBoard chessBoard){
        this.board = chessBoard.getBoard();
        this.fitness = chessBoard.getFitness();
    }

    public int getFitness(){
        return fitness;
    }

    @Override
    public int compareTo(ChessBoard chessBoard){
        return Integer.compare(chessBoard.getFitness(), fitness);
    }

}
