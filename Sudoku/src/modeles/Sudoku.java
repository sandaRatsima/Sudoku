package modeles;
public class Sudoku {
    private int[][] grid;
    private int[][] solution;

    public Sudoku(int difficulty){
        this.grid = SudokuGenerator.sudokuGenerator(difficulty);
        solution = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            solution[i] = grid[i].clone(); 
        }
        if(SudokuSolver.solveBoard(solution)){
            System.out.println("Solved Successfully");
        }else{
            System.err.println("Unlucky, unsolvable sudoku");;
        }
    }

    public int[][] getGrid(){
        return this.grid;
    }
    public static boolean isInGrid(int number, int row, int col, int[][] grid){
        return number == grid[row][col];
    }

    public int[][] getSolution(){
        return this.solution;
    }
}
