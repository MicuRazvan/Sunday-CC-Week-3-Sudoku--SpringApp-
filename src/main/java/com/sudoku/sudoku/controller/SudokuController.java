package com.sudoku.sudoku.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

    private int[][] solution;
    private final Random random = new Random();

    @GetMapping("/new")
    public int[][] newGame(@RequestParam String difficulty) {
        solution = generateSolution(new int[9][9], 0, 0);

        int[][] puzzle = new int[9][9];
        for (int i = 0; i < 9; i++) {
            puzzle[i] = solution[i].clone();
        }

        int cellsToRemove;
        switch (difficulty.toLowerCase()) {
            case "easy":
                cellsToRemove = 40;
                break;
            case "medium":
                cellsToRemove = 50;
                break;
            case "hard":
                cellsToRemove = 60;
                break;
            default:
                cellsToRemove = 45;
                break;
        }

        removeNumbers(puzzle, cellsToRemove);

        return puzzle;
    }

    @PostMapping("/verify")
    public boolean verifySolution(@RequestBody int[][] userGrid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (userGrid[i][j] != solution[i][j]) {
                    return false; // grid is incorrect
                }
            }
        }
        return true; // grid is correct
    }

    @GetMapping("/solution")
    public int[][] getSolution() {
        return this.solution;
    }

    private int[][] generateSolution(int[][] board, int row, int col) {
        //when grid done
        if (row == 9) {
            return board;
        }

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffleArray(nums);

        for (int num : nums) {
            if (isValidPlacement(board, row, col, num)) {
                board[row][col] = num;
                if (generateSolution(board, nextRow, nextCol) != null) {
                    return board;
                }
                board[row][col] = 0; // backtrack
            }
        }
        return null; // trigger backtracking
    }

    private void removeNumbers(int[][] board, int count) {
        int removed = 0;
        while (removed < count) {
            int row = random.nextInt(9);
            int col = random.nextInt(9);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                removed++;
            }
        }
    }

    //check if the placement is right while creating the sudoku grid
    private boolean isValidPlacement(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    private void shuffleArray(int[] ar) {
        for (int i = ar.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
