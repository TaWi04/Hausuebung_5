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
public class CallableSudokuCheckerGrid implements Callable<Boolean> {

    private final int[][] sudoku;

    public CallableSudokuCheckerGrid(int[][] sudoku) {
        this.sudoku = sudoku;

    }

    @Override
    public Boolean call() throws Exception {
        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) // row, col is start of the 3 by 3 grid
            {
                for (int pos = 0; pos < 8; pos++) {
                    for (int pos2 = pos + 1; pos2 < 9; pos2++) {
                        if (sudoku[row + pos % 3][col + pos / 3] == sudoku[row + pos2 % 3][col + pos2 / 3]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

}
