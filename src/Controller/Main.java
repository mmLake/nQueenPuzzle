package Controller;

import Model.ChessBoard;
import Model.GeneticAlgorithm;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(400);
        ChessBoard finalBoard = geneticAlgorithm.run();

        System.out.println(Arrays.toString(finalBoard.getBoard()) + "\n" + finalBoard.getFitness() + "\n");
    }
}
