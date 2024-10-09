package org.example;

import static org.example.Sudoku.solveWithSimulatedAnnealing;

public class Main {
    public static void main(String[] args) {
        int[] easyPuzzle = new int[] {
                0, 0, 0, 2, 6, 0, 7, 0, 1,
                6, 8, 0, 0, 7, 0, 0, 9, 0,
                1, 9, 0, 0, 0, 4, 5, 0, 0,
                8, 2, 0, 1, 0, 0, 0, 4, 0,
                0, 0, 4, 6, 0, 2, 9, 0, 0,
                0, 5, 0, 0, 0, 3, 0, 2, 8,
                0, 0, 9, 3, 0, 0, 0, 7, 4,
                0, 4, 0, 0, 5, 0, 0, 3, 6,
                7, 0, 3, 0, 1, 8, 0, 0, 0
        };
        System.out.println("Solving Easy Puzzle:");
        solveWithSimulatedAnnealing(easyPuzzle);
        System.out.println();

        int[] mediumPuzzle = new int[] {
                5, 3, 0, 0, 7, 0, 0, 0, 0,
                6, 0, 0, 1, 9, 5, 0, 0, 0,
                0, 9, 8, 0, 0, 0, 0, 6, 0,
                8, 0, 0, 0, 6, 0, 0, 0, 3,
                4, 0, 0, 8, 0, 3, 0, 0, 1,
                7, 0, 0, 0, 2, 0, 0, 0, 6,
                0, 6, 0, 0, 0, 0, 2, 8, 0,
                0, 0, 0, 4, 1, 9, 0, 0, 5,
                0, 0, 0, 0, 8, 0, 0, 7, 9
        };
        System.out.println("Solving Medium Puzzle:");
        solveWithSimulatedAnnealing(mediumPuzzle);
        System.out.println();

        int[] hardPuzzle = new int[] {
                2, 1, 0, 0, 3, 0, 0, 0, 0,
                5, 0, 0, 4, 6, 2, 0, 0, 0,
                0, 6, 8, 0, 0, 0, 0, 5, 0,
                8, 0, 0, 0, 0, 0, 0, 0, 1,
                7, 0, 0, 8, 0, 1, 0, 0, 4,
                3, 0, 0, 0, 9, 0, 0, 0, 5,
                0, 5, 0, 0, 0, 0, 9, 8, 0,
                0, 0, 0, 7, 4, 6, 0, 0, 2,
                0, 0, 0, 0, 8, 0, 0, 3, 6
        };
        System.out.println("Solving Hard Puzzle:");
        solveWithSimulatedAnnealing(hardPuzzle);
    }
}