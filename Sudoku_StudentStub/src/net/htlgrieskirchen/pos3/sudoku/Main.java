package net.htlgrieskirchen.pos3.sudoku;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        int[][] input = ss.readSudoku(new File("1_sudoku_level1.csv"));

        System.out.println(">--- ORIGINAL ---");
        ss.printSudoku(input);
        System.out.println("SOLVED    = " + ss.checkSudoku(input));
        System.out.println("SOLVEDP   = " + ss.checkSudokuParallel(ss.sudoku.getSudoku()));

        int[][] output = ss.solveSudoku(input);
        ss.solveSudokuParallel(input);
        int[][] outputP = ss.sudoku.getSudoku();

        System.out.println(">--- SOLUTION ---");
        ss.printSudoku(output);
        System.out.println(">--- SOLUTIONPARALLEL ---");
        ss.printSudoku(outputP);
        System.out.println(">----------------");
        System.out.println("SOLVED    = " + ss.checkSudoku(output));
        System.out.println("SOLVEDP   = " + ss.checkSudokuParallel(output));
        System.out.println(">----------------");
        System.out.println("Benchmark: " + ss.benchmark(input, new File("1_sudoku_level1.csv")) + "ms");
        System.out.println("BenchmarkP: " + ss.benchmarkParallel(input, new File("1_sudoku_level1.csv")) + "ms");

    }
}
