package modeles;
public class Sudoku {
    private int[][] grid;
    public Sudoku(int difficulty){
        this.grid = SudokuGenerator.sudokuGenerator(difficulty);
    }

    public int[][] getGrid(){
        return this.grid;
    }
    public static boolean isInGrid(int number, int row, int col, int[][] grid){
        return number == grid[row][col];
     }

}
