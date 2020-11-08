package net.htlgrieskirchen.pos3.sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Please enter here an answer to task four between the tags:
 * <answerTask4>
 *    Hier sollte die Antwort auf die Aufgabe 4 stehen.
 * </answerTask4>
 */
public class SudokuSolver implements ISodukoSolver {

    private static int N = 9;

    public SudokuSolver() {
        //initialize if necessary
    }

    @Override
    public final int[][] readSudoku(File file) {
        int[][] result = new int[9][9];
        AtomicInteger i = new AtomicInteger(0);
        AtomicInteger j = new AtomicInteger(0);

        try {
            if (!file.canRead() || !file.isFile()) {
                System.out.println("ERROR!");
                System.exit(0);
            } else {

            }
            Files.lines(file.toPath())
                    .map(s -> s.split(";"))
                    .forEach((s) -> {
                        Arrays.stream(s)
                                .forEach((string) -> {
                                    try {
                                        result[i.intValue()][j.intValue()] = Integer.parseInt(string);
                                    } catch (NumberFormatException e) {
                                        System.out.println("NumberFormatException!!");
                                    }
                                    j.addAndGet(1);
                                });
                        j.set(0);
                        i.addAndGet(1);
                    });

        } catch (IOException ex) {
            Logger.getLogger(SudokuSolver.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }

    @Override
    public boolean checkSudoku(int[][] rawSudoku) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 8; col++) {
                if (rawSudoku[row][col] == rawSudoku[row][col + 1]) {
                    return false;
                }
            }
        }
        //Verifies rows

        for (int col2 = 0; col2 < 9; col2++) {
            for (int row2 = 0; row2 < 8; row2++) {
                if (rawSudoku[row2][col2] == rawSudoku[row2 + 1][col2]) {
                    return false;
                }
            }
        }
        //verifies columns

        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) // row, col is start of the 3 by 3 grid
            {
                for (int pos = 0; pos < 8; pos++) {
                    for (int pos2 = pos + 1; pos2 < 9; pos2++) {
                        if (rawSudoku[row + pos % 3][col + pos / 3] == rawSudoku[row + pos2 % 3][col + pos2 / 3]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    //
    @Override
    public int[][] solveSudoku(int[][] rawSudoku) {
        solveSuduko(rawSudoku, 0, 0);
        return rawSudoku;
    }

    @Override
    public int[][] solveSudokuParallel(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }

    // add helper methods here if necessary
    public String getRowSeparator(String sep) { // method which prints a line
        StringBuilder separator = new StringBuilder();
        separator.append('\n');

        for (int i = 0; i <= 9; i++) {
            separator.append(sep);
        }
        separator.append('\n');

        return separator.toString();
    }

    static boolean isSafe(int[][] rawSudoku, int row, int col, int num) {

        for (int x = 0; x <= 8; x++) {
            if (rawSudoku[row][x] == num) {
                return false;
            }
        }

        for (int x = 0; x <= 8; x++) {
            if (rawSudoku[x][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3, startCol
                = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (rawSudoku[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static boolean solveSuduko(int grid[][], int row,
            int col) {

        if (row == N - 1 && col == N) {
            return true;
        }
        if (col == N) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0) {
            return solveSuduko(grid, row, col + 1);
        }

        for (int num = 1; num < 10; num++) {

            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSuduko(grid, row, col + 1)) {
                    return true;
                }
            }

            grid[row][col] = 0;
        }
        return false;
    }

    public String printSudoku(int[][] input) {
        int count_row = 1;
        int count_col = 1;
        String temp = "";
        for (int k = 0; k < input.length; k++) {

            for (int l = 0; l < input.length; l++) {

                if (count_row == 3) {
                    temp = " | ";
                    count_row = 1;
                } else {
                    temp = " : ";
                    count_row++;
                }
                if (input[k][l] == 0) {
                    System.out.print(" " + temp);
                } else {
                    System.out.print(input[k][l] + temp);
                }
            }
            //System.out.println("");
            if (count_col == 3) {
                temp = getRowSeparator("____");
                temp = (String) temp.subSequence(0, temp.length() - 6);
                count_col = 1;
            } else {
                temp = "";
                //temp = ss.getRowSeparator("");
                count_col++;
            }
            System.out.println(temp);
        }
        return "";
    }

    public long benchmark(int[][] rawSudoku, File file) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            readSudoku(file);
            checkSudoku(rawSudoku);
            solveSudoku(rawSudoku);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

}
