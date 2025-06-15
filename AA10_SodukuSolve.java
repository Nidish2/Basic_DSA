package dsa;

public class AA10_SodukuSolve {

	// Method to print the Sudoku board
	public static void printBoard(char[][] board) {
		for (int i = 0; i < 9; i++) {
			if (i > 0 && i % 3 == 0) {
				System.out.println("---------------------");
			}
			for (int j = 0; j < 9; j++) {
				if (j > 0 && j % 3 == 0) {
					System.out.print("| ");
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n");
	}

	// Method to solve the Sudoku puzzle using backtracking
	public static boolean solveSudoku(char[][] board) {
		return solve(board, 0, 0);
	}

	// Helper method for backtracking solution
	private static boolean solve(char[][] board, int row, int col) {
		// Check if we've filled the entire board
		if (row == 9) {
			return true; // Successfully solved the Sudoku
		}

		// Calculate the next row and column indices
		int nextRow = (col == 8) ? (row + 1) : row;
		int nextCol = (col + 1) % 9;

		// Skip already filled cells
		if (board[row][col] != '.') {
			return solve(board, nextRow, nextCol);
		}

		// Try placing numbers from '1' to '9' in the current cell
		for (char num = '1'; num <= '9'; num++) {
			if (isSafe(board, row, col, num)) {
				board[row][col] = num; // Place the number
				if (solve(board, nextRow, nextCol)) {
					return true; // Found a valid solution
				}
				board[row][col] = '.'; // Backtrack
			}
		}
		
		return false; // No valid number could be placed
	}

	// Method to check if placing 'num' in board[row][col] is safe
	private static boolean isSafe(char[][] board, int row, int col, char num) {
		// Check row and column for the presence of 'num'
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == num || board[i][col] == num) {
				return false; // 'num' already exists in row or column
			}
		}

		// Check 3x3 grid for the presence of 'num'
		int startRow = (row / 3) * 3;
		int startCol = (col / 3) * 3;
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startCol; j < startCol + 3; j++) {
				if (board[i][j] == num) {
					return false; // 'num' already exists in the 3x3 grid
				}
			}
		}
		return true; // 'num' can be safely placed in board[row][col]
	}

	// Main method to test the Sudoku solver
	public static void main(String[] args) {
		// Initialize a simple Sudoku board (9x9 grid)
		char[][] board = {
	            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
	            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
	            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
	            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
	            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
	            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
	            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
	            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
	            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
	        };

		System.out.println("Original Sudoku Board:\n");
		printBoard(board); // Print the initial board

		// Solve the Sudoku puzzle
		long start = System.nanoTime();
		if (solveSudoku(board)) {
			System.out.println("Solved Sudoku Board:\n");
			printBoard(board); // Print the solved board
		} else {
			System.out.println("No solution exists for the given Sudoku board.");
		}
		long end = System.nanoTime();
		System.out.println("The time taken to solve sudoku " + (end - start)+"\n");
	}
}