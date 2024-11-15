
public class SudokuSolver {
	public static final int GRID_SIZE = 9;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = {
				{ 8, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 3, 6, 0, 0, 0, 0, 0 },
				{ 0, 7, 0, 0, 9, 0, 2, 0, 0 },
				{ 0, 5, 0, 0, 0, 7, 0, 0, 0 },
				{ 0, 0, 0, 0, 4, 5, 7, 0, 0 },
				{ 0, 0, 0, 1, 0, 0, 0, 3, 0 },
				{ 0, 0, 1, 0, 0, 0, 0, 6, 8 },
				{ 0, 0, 8, 5, 0, 0, 0, 1, 0 },
				{ 0, 9, 0, 0, 0, 0, 4, 0, 0 } 
		};
		
		printBoard(board);

		if(solveBoard(board)){
			System.out.println("Solved Successfully");
		}
		else{System.out.println("Grid Unsolvable");}
		
		printBoard(board);
	}

	private static boolean isNumberInRow(int[][] board, int number, int row){
		for(int i = 0; i<GRID_SIZE; i++){
			if (board[row][i] == number){
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInColumn(int[][] board, int number, int column){
		for(int i = 0; i<GRID_SIZE; i++){
			if(board[i][column] == number){
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInSquare(int[][] board, int number, int row, int column){
		int localBoxRow = row - row%3;
		int localBoxColumn = column - column%3;

		for(int i = localBoxRow; i<localBoxRow + 3; i++ ){
			for(int j = localBoxColumn; j<localBoxColumn + 3; j++){
				if (board[i][j] == number){
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isValidPlacement(int[][] board,  int number, int row, int column){
		return !isNumberInRow(board, number, row) &&
				!isNumberInColumn(board, number, column) &&
				!isNumberInSquare(board, number, row, column);
	}


	private static boolean solveBoard(int [][] board){
		for(int row = 0; row<GRID_SIZE; row++){
			for(int column = 0; column < GRID_SIZE; column++){
				if(board[row][column] == 0){
					for(int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++){
						if(isValidPlacement(board, numberToTry, row, column)){
							board[row][column] = numberToTry;

							if(solveBoard(board)){
								return true;
							}
							else{
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}
	
	private static void printBoard(int[][] board){
		for(int i = 0; i< GRID_SIZE; i++){
			for(int j = 0; j< GRID_SIZE; j++){
				System.out.print(board[i][j]+ " ");
			}
			System.out.println(" ");
		}
		System.out.println("");
	}
	
}