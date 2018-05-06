package Model;

import Controller.ChessBoardController;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by mayalake on 5/6/18.
 */
public class SimulatedAnnealingAlgorithm {

    private static final double MAX_STEPS = 5000;
    private Random random = new Random();

    public SimulatedAnnealingAlgorithm(){
        ChessBoard problem = new ChessBoard();
        ChessBoard finalBoard = run(problem);

        System.out.println(Arrays.toString(finalBoard.getBoard()) + "\n" + finalBoard.getFitness() + "\n");
    }

    private ChessBoard run(ChessBoard curBoard){
        double totalSteps = 0;
        double temp  = 1.0;

        while ((totalSteps < MAX_STEPS) && (curBoard.getFitness() < ChessBoardController.MAX_FITNESS)){

            curBoard.setChessBoard(neighbor(curBoard, temp));

            temp = Math.max(temp*.99, .0000000001);

            totalSteps++;
        }

        return curBoard;
    }

    private ChessBoard neighbor(ChessBoard curBoard, double temp){
        int idx;
        int oldVal, newVal;
        int[] nextBoardTiles = curBoard.getBoard();
        ChessBoard nextBoard = new ChessBoard(curBoard);

        while (true) {
            //generate random values
            idx = random.nextInt(ChessBoardController.CHESSBOARD_SIZE);
            newVal = random.nextInt(ChessBoardController.CHESSBOARD_SIZE);

            //set old and new values
            oldVal = nextBoardTiles[idx];
            nextBoardTiles[idx] = newVal;

            //set new next board
            nextBoard.setBoard(nextBoardTiles);

            //set probability
            int dE = nextBoard.getFitness() - curBoard.getFitness();
            double probability = Math.min(1, Math.exp(dE / temp));

            //return board or reset tiles and continue while loop
            if (curBoard.getFitness() < nextBoard.getFitness()) {
                return nextBoard;
            } else if (probability > Math.random()) {
                return nextBoard;
            } else{
                nextBoardTiles[idx] = oldVal;
            }
        }
    }
}
