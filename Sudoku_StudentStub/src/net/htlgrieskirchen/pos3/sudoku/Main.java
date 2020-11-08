package net.htlgrieskirchen.pos3.sudoku;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        int[][] input = ss.readSudoku(new File("1_sudoku_level1.csv"));

        System.out.println(">--- ORIGINAL ---");
        ss.printSudoku(input);
        System.out.println("SOLVED    = " + ss.checkSudoku(input));
        int[][] output = ss.solveSudoku(input);
        System.out.println(">--- SOLUTION ---");
        ss.printSudoku(output);
        System.out.println(">----------------");
        System.out.println("SOLVED    = " + ss.checkSudoku(output)); //ss.checkSudoku(output));
        System.out.println(">----------------");
        System.out.println("Benchmark: " + ss.benchmark(input, new File("1_sudoku_level1.csv")) + "ms");
    }
}
