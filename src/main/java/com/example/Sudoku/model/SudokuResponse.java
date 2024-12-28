package com.example.Sudoku.model;

public class SudokuResponse {
    private String puzzle;

    public SudokuResponse(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
    }
}
