import java.util.Random;

public class SudokuGenerator {
	public static final int GRID_SIZE = 9;
	public static final int EASY = 1;
	public static final int MEDIUM = 2;
	public static final int HARD = 3;
	
	public static int[][]sudokuGenerator(int difficulty) {
		int[][] grid = {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
		};
		int nbToBlank = 0;
		switch(difficulty) {
		case EASY :
			nbToBlank = 45;
			break;
		case MEDIUM :
			nbToBlank = 54;
			break;
		case HARD :
			nbToBlank = 64;
			break;
		}
		Random rand = new Random();
		for(int i = 0; i < GRID_SIZE; i++) {
			int number = rand.nextInt(9)+1;
			if(SudokuSolver.isValidPlacement(grid, number, i, 0)) {
				grid[i][0] = number; 
			}
		}
		SudokuSolver.solveBoard(grid);
		for(int i = 0; i <nbToBlank; i++) {
			int rowToBlank = rand.nextInt(9);
			int colToBlank = rand.nextInt(9);
			
			grid[rowToBlank][colToBlank] = 0;
		}
		
		
		return grid;
	}
}
