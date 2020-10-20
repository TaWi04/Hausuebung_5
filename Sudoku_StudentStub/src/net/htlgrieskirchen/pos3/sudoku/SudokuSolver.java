package net.htlgrieskirchen.pos3.sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Please enter here an answer to task four between the tags:
 * <answerTask4>
 *    Hier sollte die Antwort auf die Aufgabe 4 stehen.
 * </answerTask4>
 */
public class SudokuSolver implements ISodukoSolver {
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
            }
            else{
                
            }
             Files.lines(file.toPath())
                    .map(s -> s.split(";"))
                    .forEach((s) -> {
                            Arrays.stream(s)
                            .forEach((string) ->{
                                    try {
                                        result[i.intValue()][j.intValue()]  = Integer.parseInt(string);
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
        Map<Integer,Integer[]> row = new HashMap();
        for (int[] rawSudoku1 : rawSudoku) { // row
            for (int k = 0; k < 3; k++) {
                int toCheck = rawSudoku1[k];
                for (int l = 0; l < 3; l++) {    
                    for (int m = 0; m < 3; m++) {
                        if (toCheck == rawSudoku[l][m]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public int[][] solveSudoku(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }
    
    @Override
    public int[][] solveSudokuParallel(int[][] rawSudoku) {
        // implement this method
        return new int[0][0]; // delete this line!
    }

    // add helper methods here if necessary
     public String getRowSeparator(String sep){ // method which prints a line
        StringBuilder separator = new StringBuilder();
        separator.append('\n');

            for (int i = 0; i <= 9; i++) {
                separator.append(sep);
               // System.out.print("-");
            }
            //separator.append('_');
            //System.out.print("+");
        separator.append('\n');
        
        return separator.toString();
    }
}
