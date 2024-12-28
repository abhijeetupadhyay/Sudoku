package com.example.Sudoku.controller;

import com.example.Sudoku.model.SudokuResponse;
import com.example.Sudoku.service.SudokuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {
    private final SudokuService sudokuService;

    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    @GetMapping("/generate")
    public SudokuResponse generateSudoku() {
        return new SudokuResponse(sudokuService.generateSudoku());
    }

}
