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
public class CallableSudokuCheckerRow implements Callable<Boolean> {

    private final int[][] sudoku;

    public CallableSudokuCheckerRow(int[][] sudoku) {
        this.sudoku = sudoku;

    }

    @Override
    public Boolean call() throws Exception {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 8; col++) {
                if (sudoku[row][col] == sudoku[row][col + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

}
