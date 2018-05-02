package Controller;

import Model.ChessBoard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by mayalake on 5/1/18.
 */
public class ChessBoardController {
    public static final int CHESSBOARD_SIZE = 21;
    public static final int MAX_FITNESS = CHESSBOARD_SIZE*(CHESSBOARD_SIZE-1)/2;

    private static Random random = new Random();

    public static int[] createBoard(){
        int[] chessboard = new int[CHESSBOARD_SIZE];

        for (int i=0; i<CHESSBOARD_SIZE; i++){
            chessboard[i] = random.nextInt(CHESSBOARD_SIZE);
        }

        return chessboard;
    }

    public static int setFitness(int[] chessboard){
        int fitness = MAX_FITNESS;

        for (int i=0; i < CHESSBOARD_SIZE; i++){
            for (int j=i+1; j < CHESSBOARD_SIZE;j++){

                //queens in the same row
                if (chessboard[i]==chessboard[j]){
                    fitness--;
                }

                //queens in the same diagonal
                if (Math.abs(chessboard[i] - chessboard[j]) == Math.abs(i-j)){
                    fitness--;
                }
            }
        }

        return fitness;
    }

}
