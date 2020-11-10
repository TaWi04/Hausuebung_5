package net.htlgrieskirchen.pos3.sudoku;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        File level1_1 = new File("1_sudoku_level1.csv");
        File level1_2 = new File("2_sudoku_level1.csv");
        File level2_3 = new File("3_sudoku_level2.csv");

        SudokuSolver ss = new SudokuSolver();
        int[][] input = ss.readSudoku(level1_1);

        System.out.println(">--- ORIGINAL ---");
        ss.printSudoku(input);
        System.out.println("SOLVED    = " + ss.checkSudoku(input));
        //System.out.println("SOLVEDP   = " + ss.checkSudokuParallel(ss.sudoku.getSudoku()));

        int[][] output = ss.solveSudoku(input);
        //ss.solveSudokuParallel(input);
        // int[][] outputP = ss.sudoku.getSudoku();

        System.out.println(">--- SOLUTION ---");
        ss.printSudoku(output);
        //System.out.println(">--- SOLUTIONPARALLEL ---");
        //ss.printSudoku(outputP);
        System.out.println(">----------------");
        System.out.println("SOLVED    = " + ss.checkSudoku(output));
        //System.out.println("SOLVEDP   = " + ss.checkSudokuParallel(outputP));
        System.out.println(">----------------");
        System.out.println("Benchmark: " + ss.benchmark(input, level1_1) + "ms");
        //System.out.println("BenchmarkP: " + ss.benchmarkParallel(input, level1_1) + "ms");

    }
}
