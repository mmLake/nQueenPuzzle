package Model;

import Controller.ChessBoardController;
import Model.ChessBoard;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by mayalake on 5/1/18.
 */
public class GeneticAlgorithm {
    private Random random = new Random();
    private PriorityQueue<ChessBoard> population = new PriorityQueue<>(new ChessBoardComparator());

    public GeneticAlgorithm(int populationSize){
        for (int i=0; i < populationSize; i++){
            population.add(new ChessBoard());
        }
    }

    public ChessBoard run(){
        ChessBoard parentA;
        ChessBoard parentB;
        ChessBoard child;

        PriorityQueue<ChessBoard> newPopulation = new PriorityQueue<>(new ChessBoardComparator());

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
        PriorityQueue<ChessBoard> temporary = new PriorityQueue<>(new ChessBoardComparator());
        temporary.addAll(population);

        ChessBoard randomBoard = null;
        while (temporary.peek()!=null){
            randomBoard = temporary.poll();

            if (random.nextBoolean()){
                return randomBoard;
            }
        }

        System.out.println("STRANGE 1");
        return randomBoard;
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

    protected class ChessBoardComparator implements Comparator<ChessBoard> {
        @Override
        public int compare(ChessBoard x, ChessBoard y){
            return y.getFitness() - x.getFitness();
        }
    }
}