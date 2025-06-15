package dsa;

import java.util.ArrayList;
import java.util.List;

//This is on 1 of the imp as well as best Usage of backtracking.

public class A9_NQueens {

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> allBoards = new ArrayList<List<String>>();
		char[][] board = new char[n][n];
		helper(board, allBoards, 0);
		return allBoards;
	}

	// This is the Helper method to recursively solve N-Queens using backtracking
	public void helper(char[][] board, List<List<String>> allBoards, int col) {
		if (col == board.length) {
			saveBoard(board, allBoards);
			return;
		}
		for (int row = 0; row < board.length; row++) {
			if (isSafe(row, col, board)) {
				board[row][col] = 'Q';
				helper(board, allBoards, col + 1);
				board[row][col] = '.';
			}
		}
	}

	// Check if placing a queen at board[row][col] is safe
	public boolean isSafe(int row, int col, char[][] board) {
		// horizontal and Vertical
		for (int j = 0; j < board.length; j++) {
			if (board[row][j] == 'Q')
				return false;
			if (board[j][col] == 'Q')
				return false;
		}
		// upperLeft
		int r = row;
		for (int c = col; c >= 0 && r >= 0; c--, r--) {
			if (board[r][c] == 'Q')
				return false;
		}
		// upperRight
		r = row;
		for (int c = col; c < board.length && r >= 0; c++, r--) {
			if (board[r][c] == 'Q')
				return false;
		}
		// lowerLeft
		r = row;
		for (int c = col; c >= 0 && r < board.length; c--, r++) {
			if (board[r][c] == 'Q')
				return false;
		}
		// lowerRight
		r = row;
		for (int c = col; c < board.length && r < board.length; c++, r++) {
			if (board[r][c] == 'Q')
				return false;
		}
		return true;
	}

	// Save the current board configuration to the list of solutions.
	public void saveBoard(char[][] board, List<List<String>> allBoards) {
		StringBuilder row = new StringBuilder();
		List<String> newBoard = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			row.setLength(0);
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 'Q') {
					row.append('Q');
				} else {
					row.append('.');
				}
			}
			newBoard.add(row.toString());
		}
		allBoards.add(newBoard);
	}

	public static void main(String[] args) {
		A9_NQueens nQueens = new A9_NQueens();
		List<List<String>> solutions = nQueens.solveNQueens(8);
		System.out.println("Number of solutions: " + solutions.size() + "\n");
		for (List<String> solution : solutions) {
			for (String row : solution) {
				System.out.println(row);
			}
			System.out.println();
		}
	}
}
