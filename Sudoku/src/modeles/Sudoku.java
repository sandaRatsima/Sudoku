package modeles;
public class Sudoku {
    private int[][] grid;
    public Sudoku(int difficulty){
        this.grid = SudokuGenerator.sudokuGenerator(difficulty);
    }

    public int[][] getGrid(){
        return this.grid;
    }

}
