package Model;

import Controller.ChessBoardController;
import Model.ChessBoard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by mayalake on 5/1/18.
 */
public class GeneticAlgorithm {
    private Random random = new Random();

    public GeneticAlgorithm(int populationSize){
        PriorityQueue<ChessBoard> population = new PriorityQueue<>();

        for (int i=0; i < populationSize; i++){
            population.add(new ChessBoard());
        }

        ChessBoard finalBoard = run(population);

        System.out.println(Arrays.toString(finalBoard.getBoard()) + "\n" + finalBoard.getFitness() + "\n");
    }

    private ChessBoard run(PriorityQueue<ChessBoard> population){
        ChessBoard parentA;
        ChessBoard parentB;
        ChessBoard child;

        PriorityQueue<ChessBoard> newPopulation = new PriorityQueue<>();

        while (population.peek().getFitness() != ChessBoardController.MAX_FITNESS) {

            for (int i = 0; i < population.size(); i++) {
                parentA = randomSelection(population);
                parentB = randomSelection(population);
                child = reproduce(parentA, parentB);

                if (random.nextBoolean()) {
                    child = mutate(child);
                }

                newPopulation.add(child);
            }

            population.clear();
            population.addAll(newPopulation);
            newPopulation.clear();
        }

        return population.peek();
    }

    private ChessBoard randomSelection(PriorityQueue<ChessBoard> population){
        PriorityQueue<ChessBoard> temporary = new PriorityQueue<>();
        temporary.addAll(population);

        ChessBoard randomBoard = null;
        while (temporary.peek()!=null){
            randomBoard = temporary.poll();

            if (random.nextBoolean()){
                return randomBoard;
            }
        }

        return null;
    }

    private ChessBoard reproduce(ChessBoard parentA, ChessBoard parentB){
        int split = random.nextInt(ChessBoardController.CHESSBOARD_SIZE);
        int[] newBoard = new int[ChessBoardController.CHESSBOARD_SIZE];

        System.arraycopy(parentA.getBoard(), 0, newBoard, 0, split);
        System.arraycopy(parentB.getBoard(), split, newBoard, split, ChessBoardController.CHESSBOARD_SIZE-split);

        return new ChessBoard(newBoard);
    }

    private ChessBoard mutate(ChessBoard chessBoard){
        int replace = random.nextInt(ChessBoardController.CHESSBOARD_SIZE);
        int[] updatedBoard = chessBoard.getBoard();

        updatedBoard[replace] = random.nextInt(ChessBoardController.CHESSBOARD_SIZE);

        return new ChessBoard(updatedBoard);
    }

}
