/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.pos3.sudoku;

import java.util.concurrent.Callable;

/**
 *
 * @author Tamara
 */
public class CallableSudokuSolver implements Callable<Boolean> {

    private Sudoku sudoku;
    private final int row;
    private final int col;

    public CallableSudokuSolver(Sudoku sudoku, int row, int col) {
        this.sudoku = sudoku;
        this.row = row;
        this.col = col;
    }

    @Override
    public Boolean call() throws Exception {
        SudokuSolver.solveSuduko(sudoku.getSudoku(), row, col);
        return false;
    }
}
