package org.example;

import java.util.*;

public class Sudoku {
    private int[] sudokuGrid;
    private Set<Integer> originalGrid;

    public Sudoku(int[] data) {
        this.sudokuGrid = data.clone();
        this.originalGrid = new HashSet<>();
        // 81 cells
        for (int i = 0; i < 81; i++) {
            if (data[i] > 0) {
                this.originalGrid.add(i);
            }
        }
    }

    public Sudoku(int[] data, Set<Integer> originalGrid) {
        this.sudokuGrid = data.clone();
        this.originalGrid = new HashSet<>(originalGrid);
    }

    public void initializeEmptyCells() {
        for (int i = 0; i < 9; i++) {
            List<Integer> section = fetchGridSection(i);
            List<Integer> newSection = new ArrayList<>();
            boolean[] used = new boolean[10];

            for (int index : section) {
                if (sudokuGrid[index] == 0) {
                    newSection.add(index);
                } else {
                    used[sudokuGrid[index]] = true;
                }
            }

            List<Integer> toFill = new ArrayList<>();
            for (int z = 1; z <= 9; z++) {
                if (!used[z]) {
                    toFill.add(z);
                }
            }
            Collections.shuffle(toFill);

            for (int j = 0; j < newSection.size(); j++) {
                sudokuGrid[newSection.get(j)] = toFill.get(j);
            }
        }
    }

    public List<Integer> fetchGridSection(int item) {
        List<Integer> indices = new ArrayList<>(9);
        int baseRow = (item / 3) * 27;
        int baseCol = (item % 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                indices.add(baseRow + baseCol + i * 9 + j);
            }
        }
        return indices;
    }

    public void displaySudoku() {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("-------------------------");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(sudokuGrid[i * 9 + j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------------------");
    }

    public int scoreBoard() {
        int score = 0;

        // Calculate penalty for rows
        for (int i = 0; i < 9; i++) {
            score += penalizeDuplicates(getValues(i, "row"));
        }

        // Calculate penalty for columns
        for (int i = 0; i < 9; i++) {
            score += penalizeDuplicates(getValues(i, "col"));
        }

        // Calculate penalty for blocks
        for (int i = 0; i < 9; i++) {
            score += penalizeDuplicates(getValues(i, "block"));
        }

        return score;
    }


    private int penalizeDuplicates(List<Integer> values) {
        int[] counts = new int[10];
        int penalty = 0;

        for (int val : values) {
            if (val > 0) counts[val]++;
        }

        for (int i = 1; i <= 9; i++) {
            if (counts[i] > 1) {
                penalty += (counts[i] - 1);
            }
            if (counts[i] == 0) {
                penalty += 1;
            }
        }
        return penalty;
    }

    public int[] proposeGridChange() {
        int[] sudoku = sudokuGrid.clone();
        int block = new Random().nextInt(9);
        List<Integer> section = fetchGridSection(block);
        List<Integer> newSection = new ArrayList<>();

        for (int index : section) {
            if (!originalGrid.contains(index)) {
                newSection.add(index);
            }
        }

        if (newSection.size() >= 2) {
            Collections.shuffle(newSection);
            int square1 = newSection.get(0);
            int square2 = newSection.get(1);

            int temp = sudoku[square1];
            sudoku[square1] = sudoku[square2];
            sudoku[square2] = temp;
        }

        return sudoku;
    }

    public List<Integer> getValues(int index, String type) {
        List<Integer> values = new ArrayList<>(9);

        if (Objects.equals(type, "block")) {
            for (int i : fetchGridSection(index)) {
                values.add(sudokuGrid[i]);
            }
        } else if (Objects.equals(type, "row")) {
            for (int i = 0; i < 9; i++) {
                values.add(sudokuGrid[index * 9 + i]);
            }
        } else {
            for (int i = 0; i < 9; i++) {
                values.add(sudokuGrid[index + 9 * i]);
            }
        }

        return values;
    }

    public static void solveWithSimulatedAnnealing(int[] inputSudokuGrid) {
        Sudoku sudoku = new Sudoku(inputSudokuGrid);
        sudoku.displaySudoku();
        sudoku.initializeEmptyCells();

        Sudoku bestSudoku = new Sudoku(sudoku.sudokuGrid, sudoku.originalGrid);
        int currentScore = sudoku.scoreBoard();
        int bestScore = currentScore;

        double T = 1.0;
        double coolingRate = 0.99995;
        int count = 0;
        int stagnation = 0;

        long startTime = System.nanoTime();

        while (count < 500000) {

            int[] candidateData = sudoku.proposeGridChange();
            Sudoku testGrid = new Sudoku(candidateData, sudoku.originalGrid);
            int testScore = testGrid.scoreBoard();
            double delta = currentScore - testScore;

            if (delta > 0 || Math.exp(delta / T) > Math.random()) {
                sudoku = testGrid;
                currentScore = testScore;
                stagnation = 0;
            } else {
                stagnation++;
            }

            if (currentScore < bestScore) {
                bestSudoku = new Sudoku(sudoku.sudokuGrid, sudoku.originalGrid);
                bestScore = bestSudoku.scoreBoard();
            }

            if (bestScore == 0) {
                break;
            }

            T *= coolingRate;

            // Restart process after significant stagnation
            if (stagnation > 10000) {
                sudoku.initializeEmptyCells();
                stagnation = 0;
            }

            count++;
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        if (bestScore == 0) {
            System.out.println("\nSUCCESS");
        } else {
            System.out.println("\nERROR");
        }

        System.out.println("\nFilled sudoku using SA:");
        bestSudoku.displaySudoku();

        System.out.printf("Execution time: %.3f seconds\n", executionTime / 1e9);
        System.out.printf("Final penalty points: %d\n", bestScore);
    }
}