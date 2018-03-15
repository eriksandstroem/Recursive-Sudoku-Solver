package sudokusolver;
/**
 * Sudoku Solver class. Used to solve an arbitrary sudoku.
 * 
 * @author Erik Sandström
 */
public class SudokuSolver {
	private int[][] board;

	
	/**
	 * Creates a new instance of the Sudoku Solver class.
	 */
	public SudokuSolver() {
		board = new int[9][9];
	}
	/**
	 * Sets the value from the specified location of board.
	 * @param r: The row of the board
	 * @param c: The column of the board
	 * @param number: The value to be set
	 */
	public void set(int r, int c, int number) {
		board[r][c] = number;
	}
	/**
	 * Retrieves the value from the specified location of board.
	 * @param r: The row of the board
	 * @param c: The column of the board
	 * @return The value in the specified location of the board.
	 */
	public int get(int r, int c) {
		return board[r][c];
	}
	/** 
	 * Checks if the prefilled numbers of the board comply with 
	 * the rules of sudoku before an attempt to solve it is 
	 * performed.
	 * @return true if the prefilled numbers comply
	 * with the rules of sudoku.
	 */
	public boolean checkStart() {
		for(int row = 0; row <= 8; row++) {
			for(int col = 0; col <= 8; col++) {
				if(board[row][col] != 0) {
					for (int k = 0; k <= 8; k++) { 
						if ((k != col && board[row][k] == board[row][col])
								|| (k != row && board[k][col] == board[row][col])) {
							return false;
						}
					}
					if (0 <= row && row <= 2) {
						if (0 <= col && col <= 2) {
							for (int r = 0; r <= 2; r++) {
								for (int c = 0; c <= 2; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						} else if (3 <= col && col <= 5) {
							for (int r = 0; r <= 2; r++) {
								for (int c = 3; c <= 5; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						} else if (6 <= col && col <= 8) {
							for (int r = 0; r <= 2; r++) {
								for (int c = 6; c <= 8; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						}
					} else if (3 <= row && row <= 5) {
						if (0 <= col && col <= 2) {
							for (int r = 3; r <= 5; r++) {
								for (int c = 0; c <= 2; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						} else if (3 <= col && col <= 5) {
							for (int r = 3; r <= 5; r++) {
								for (int c = 3; c <= 5; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						} else if (6 <= col && col <= 8) {
							for (int r = 3; r <= 5; r++) {
								for (int c = 6; c <= 8; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						}
					} else if (6 <= row && row <= 8) {
						if (0 <= col && col <= 2) {
							for (int r = 6; r <= 8; r++) {
								for (int c = 0; c <= 2; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						} else if (3 <= col && col <= 5) {
							for (int r = 6; r <= 8; r++) {
								for (int c = 3; c <= 5; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						} else if (6 <= col && col <= 8) {
							for (int r = 6; r <= 8; r++) {
								for (int c = 6; c <= 8; c++) {
									if ((col != c && board[row][col] == board[r][c])
											|| (r != row && board[row][col] == board[r][c])) {
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
	/**
	 * Retrieves the solution, if a solution was found, in the form of a multidimensional array stored in '
	 * board. Otherwise nothing happens to the board.
	 * @return True if a solution was found and false if no solution exists
	 */
	public boolean solver() {
		return solve(0, 0);
	}
	/** Private recursive method that is used for the solver method.
	 */
	private boolean solve(int i, int j) {
		if (board[i][j] == 0) {
			for (int k = 1; k <= 9; k++) {
				board[i][j] = k;
				boolean next = false;
				if (checkRules(i, j)) {
					if (i == 8 && j == 8) {
						return true;
					} else if (j == 8) {
						next = solve(i + 1, 0);
					} else {
						next = solve(i, j + 1);
					}
				}
				if(next == true) {
					return true;
				}
			}
			board[i][j] = 0;
			return false;
		} else {
			boolean next = false;
			if (checkRules(i, j)) {
				if (i == 8 && j == 8) {
					return true;
				} else if (j == 8) {
					 next = solve(i + 1, 0);
				} else {
					 next = solve(i, j + 1);
				}
			}
			if(next == true) {
				return true;
			}
		}
		return false;
	}
	/** Checks if the rules of sudoku are fulfilled at an arbitrary
	 * location in the board.
	 * @param row: the row where the rules are checked
	 * @param col: the column where the rules are checked
	 * @return true if the rules are complied 
	 *  */
	private boolean checkRules(int row, int col) {
		for (int k = 0; k <= 8; k++) { 
			if ((k != col && board[row][k] == board[row][col])
					|| (k != row && board[k][col] == board[row][col])) {
				return false;
			}
		}
		if (0 <= row && row <= 2) {
			if (0 <= col && col <= 2) {
				for (int r = 0; r <= 2; r++) {
					for (int c = 0; c <= 2; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			} else if (3 <= col && col <= 5) {
				for (int r = 0; r <= 2; r++) {
					for (int c = 3; c <= 5; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			} else if (6 <= col && col <= 8) {
				for (int r = 0; r <= 2; r++) {
					for (int c = 6; c <= 8; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			}
		} else if (3 <= row && row <= 5) {
			if (0 <= col && col <= 2) {
				for (int r = 3; r <= 5; r++) {
					for (int c = 0; c <= 2; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			} else if (3 <= col && col <= 5) {
				for (int r = 3; r <= 5; r++) {
					for (int c = 3; c <= 5; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			} else if (6 <= col && col <= 8) {
				for (int r = 3; r <= 5; r++) {
					for (int c = 6; c <= 8; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			}
		} else if (6 <= row && row <= 8) {
			if (0 <= col && col <= 2) {
				for (int r = 6; r <= 8; r++) {
					for (int c = 0; c <= 2; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			} else if (3 <= col && col <= 5) {
				for (int r = 6; r <= 8; r++) {
					for (int c = 3; c <= 5; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			} else if (6 <= col && col <= 8) {
				for (int r = 6; r <= 8; r++) {
					for (int c = 6; c <= 8; c++) {
						if ((col != c && board[row][col] == board[r][c])
								|| (r != row && board[row][col] == board[r][c])) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * For testing purposes
	 */
	public static void main(String[] args) {
		SudokuSolver game = new SudokuSolver();
		game.set(0, 2, 8);
		game.set(0, 5, 9);
		game.set(0, 7, 6);
		game.set(0, 8, 2);
		game.set(1, 8, 5);
		game.set(2, 0, 1);
		game.set(2, 2, 2);
		game.set(2, 3, 5);
		game.set(3, 3, 2);
		game.set(3, 4, 1);
		game.set(3, 7, 9);
		game.set(4, 1, 5);
		game.set(4, 6, 6);
		game.set(5, 0, 6);
		game.set(5, 7, 2);
		game.set(5, 8, 8);
		game.set(6, 0, 4);
		game.set(6, 1, 1);
		game.set(6, 3, 6);
		game.set(6, 5, 8);
		game.set(7, 0, 8);
		game.set(7, 1, 6);
		game.set(7, 4, 3);
		game.set(7, 6, 1);
		game.set(8, 6, 4);
		
	
	
		
		
		if(game.checkStart()) {
		boolean b = game.solver();
		
		
		for(int r = 0; r <= 8; r++) {
			for(int c = 0; c <= 8; c++) {
				if(c == 2 || c == 5) {
					System.out.print(game.get(r, c) + "  ");
				}
				else if(c == 8) {
					System.out.print(game.get(r, c) + "\n");
				}
				else {
					System.out.print(game.get(r, c) + " ");
				}
			}
		}
		System.out.print(b);
	
		}
		else {
			System.out.print(false);
		}
	}
}
