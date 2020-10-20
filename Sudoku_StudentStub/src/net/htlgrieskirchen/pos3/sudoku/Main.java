package net.htlgrieskirchen.pos3.sudoku;


import java.io.File;

public class Main {
    public static void main(String[] args) {
        SudokuSolver ss = new SudokuSolver();
        int[][] input = ss.readSudoku(new File("1_sudoku_level1.csv"));
        
        System.out.println(">--- ORIGINAL ---");
        int count_row = 1;
        int count_col = 1;
        String temp = "";
         for (int k = 0; k < input.length; k++) {
             
                for (int l = 0; l < input.length; l++) {
                    
                    if(count_row == 3){
                        temp = " | ";
                        count_row = 1;
                    }else{
                        temp = " : ";
                        count_row++;
                    }
                     if(input[k][l] == 0){
                         System.out.print(" " + temp);
                    }else{
                          System.out.print(input[k][l]+ temp );
                    }
                }
                //System.out.println("");
                if(count_col == 3){
                    temp = ss.getRowSeparator("____");
                    temp =(String) temp.subSequence(0, temp.length()-6);
                    count_col = 1;
                }
                else{
                    temp = "";
                    //temp = ss.getRowSeparator("");
                    count_col++;
                }
                System.out.println(temp);
            }
        int[][] output = ss.solveSudoku(input);
        System.out.println(">--- SOLUTION ---");
        // print the sudoku if you want
        System.out.println(">----------------");
        System.out.println("SOLVED    = " + ss.checkSudoku(output));
        System.out.println(">----------------");
    }
}
