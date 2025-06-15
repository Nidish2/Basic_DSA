package dsa;

import java.util.ArrayList;
import java.util.List;

//Placing n Knights in a n*n chess board

public class AA11_NKnights {

	public List<List<String>> knightlOnAChessboard(int n) {
		List<List<String>> allBoards = new ArrayList<>();
		char[][] board = new char[n][n];
		placeNKnights(allBoards, board, 0);
		return allBoards;
	}

	private void placeNKnights(List<List<String>> allBoards, char[][] board, int row) {
		if (row == board.length) {
			saveBoard(allBoards, board);
			return;
		}
		for (int col = 0; col < board.length; col++) {
			if (isSafe(board, row, col)) {
				board[row][col] = 'K';
				placeNKnights(allBoards, board, row + 1);
				board[row][col] = '.';
			}
		}
	}

	private boolean isSafe(char[][] board, int row, int col) {
		int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };

		for (int i = 0; i < 8; i++) {
			int newRow = row + dr[i];
			int newCol = col + dc[i];
			if (isValid(board, newRow, newCol) && board[newRow][newCol] == 'K') {
				return false;
			}
		}
		return true;
	}

	private boolean isValid(char[][] board, int row, int col) {
		return row >= 0 && row < board.length && col >= 0 && col < board.length;
	}

	private void saveBoard(List<List<String>> allBoards, char[][] board) {
		List<String> newBoard = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'K')
					sb.append('K');
				else
					sb.append('.');
			}
			newBoard.add(sb.toString());
		}
		allBoards.add(newBoard);
	}

	public static void main(String[] args) {
		AA11_NKnights b = new AA11_NKnights();
		List<List<String>> allBoards = b.knightlOnAChessboard(4);
		System.out.println("Total number of solutions: " + allBoards.size() + "\n");
		for (List<String> board : allBoards) {
			for (String s : board) {
				System.out.println(s);
			}
			System.out.println();
		}
	}
}