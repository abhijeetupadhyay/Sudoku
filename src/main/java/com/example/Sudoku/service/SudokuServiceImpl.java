package com.example.Sudoku.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.example.Sudoku.utils.ApplicationConstants.GRID_SIZE;
import static com.example.Sudoku.utils.ApplicationConstants.SUBGRID_SIZE;

@Service
public class SudokuServiceImpl implements SudokuService {
    @Override
    public String generateSudoku() {
        int[][] sudoku = new int[GRID_SIZE][GRID_SIZE];
        fillBoard(sudoku);
        removeNumbers(sudoku, 40);
        //form the API response
        String s = "";
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                s += sudoku[i][j];
            }
            s += "\n";
        }
        return s;
    }

    public boolean isValidSudoku(int[][] sudoku, int row, int col, int num) {
        for(int i = 0; i < GRID_SIZE; i++) {
            if (sudoku[row][i] == num || sudoku[i][col] == num) {
                return false;
            }
        }
        //check subgrid
        int startRow = row - row % SUBGRID_SIZE;
        int startCol = col - col % SUBGRID_SIZE;
        for (int i = 0; i < SUBGRID_SIZE; i++) {
            for (int j = 0; j < SUBGRID_SIZE; j++) {
                if (sudoku[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
    // Function to fill the Sudoku board using backtracking
    public boolean fillBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    List<Integer> numbers = new ArrayList<>();
                    for (int i = 1; i <= GRID_SIZE; i++) {
                        numbers.add(i);
                    }
                    Collections.shuffle(numbers); // Shuffle numbers to add randomness
                    for (int num : numbers) {
                        if (isValidSudoku(board, row, col, num)) {
                            board[row][col] = num;
                            if (fillBoard(board)) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false; // No valid number found, backtrack
                }
            }
        }
        return true;
    }

    // Function to remove numbers from the board to create a puzzle
    public static void removeNumbers(int[][] board, int numRemove) {
        Random rand = new Random();
        int count = 0;
        while (count < numRemove) {
            int row = rand.nextInt(GRID_SIZE);
            int col = rand.nextInt(GRID_SIZE);
            if (board[row][col] != 0) {
                board[row][col] = 0;
                count++;
            }
        }
    }
    //todo:// to generate on the basis of 3 modes: easy, medium, hard and advanced level
    //todo:// replace 0 with _ when sending response; format response in new lines
    //todo:// setup docker and pipelines for this
}
