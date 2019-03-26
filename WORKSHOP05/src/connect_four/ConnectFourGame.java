package connect_four;

import java.util.Scanner;

public class ConnectFourGame {
	// outer - rows, inner - column
	final int matchLen = 4, numOfCols = 7, numOfRows = 6;
	int[][] grid = new int[numOfCols][numOfRows];

	public ConnectFourGame(Scanner in) {
		// Scanner in = new Scanner(System.in);

		// yellow = 1, red = 2
		int input = 0;
		boolean error = true;
		String player;
		for (int i = 1; i <= numOfRows * numOfCols; i = error ? i : i + 1) {
			player = i % 2 != 0 ? "yellow" : "red";
			// get drop column for current turn
			display();
			input = prompt(in, player);

			// check if there i space to drop
			if (grid[input][numOfRows - 1] == 0) {
				process(input, i);

				if (checkColumn() || checkRow() || checkDiag()) {
					display();
					System.out.format("\n*********\n%s wins\n*********\n", player);
					break;
				}

				display();
				error = false;
			} else {
				System.out.println("\nColumn full, select another column!");
				error = true;
			}

			if (i == numOfCols * numOfRows) {
				System.out.println("DRAW!!!!");
			}
		}

		// in.close();
	}

	boolean checkDiag() {
		// posDiag
		for (int r = 0; r <= (numOfRows - matchLen); ++r) {
			for (int c = 0; c <= (numOfCols - matchLen); ++c) {
				if (grid[c][r] != 0) {
					int i;
					for (i = 1; i < matchLen; ++i) {
						if (grid[c][r] != grid[c + i][r + i]) {
							break;
						}
					}
					if (i == matchLen) {
						System.out.println("pos baddy");
						return true;
					}
				}
			}
		}
		// negDiag
		for (int r = (matchLen - 1); r < numOfRows; ++r) {
			for (int c = 0; c < (numOfCols - matchLen); ++c) {
				if (grid[c][r] != 0) {
					int i;
					for (i = 1; i < matchLen; ++i) {
						if (grid[c][r] != grid[c + i][r - i]) {
							break;
						}
					}
					if (i == matchLen) {
						return true;
					}
				}
			}
		}

		return false;
	}

	boolean checkColumn() {
		// for each column from 0 to end
		for (int c = 0; c < numOfCols; ++c) {
			// for each row that can have match rows after it
			for (int r = 0; r <= (numOfRows - matchLen); ++r) {
				// if the value is not 0
				if (grid[c][r] != 0) {
					// i is used to track matches
					int i;
					for (i = 1; i < matchLen; ++i) {
						if (grid[c][r] != grid[c][r + i]) {
							// break loop if no matches, pointless remaining
							// rows for current coordinate
							break;
						}
					}
					// if there was a match i will be four
					if (i == matchLen) {
						return true;
					}
				}

			}
		}
		// return false if nothing
		return false;
	}

	boolean checkRow() {
		// see comments for check column
		for (int r = 0; r < numOfRows; ++r) {
			for (int c = 0; c <= (numOfCols - matchLen); ++c) {
				if (grid[c][r] != 0) {
					int i;
					for (i = 1; i < matchLen; ++i) {
						if (grid[c][r] != grid[c + i][r]) {
							break;
						}
					}
					if (i == matchLen) {
						return true;
					}
				}
			}
		}

		return false;
	}

	void process(int input, int turn) {
		for (int i = 0; i < grid[input].length; ++i) {
			if (grid[input][i] == 0) {
				grid[input][i] = turn % 2 != 0 ? 1 : 2;
				break;
			}
		}

	}

	int prompt(Scanner in, String player) {
		int i = -1;
		// column range has to be between 0 and 6
		while (i < 1 || i > numOfCols) {
			System.out.format("Drop a %s disk at column (1-%d): ", player, numOfCols);

			// loop until valid int
			while (!in.hasNextInt()) {
				System.out.format("Drop a %s disk at column (1-%d): ", player, numOfCols);
				in.next();
			}
			i = in.nextInt();
		}
		return i - 1;
	}

	void display() {
		System.out.println();
		// heading
		System.out.print("|");
		for (int i = 1; i <= numOfCols; i++) {
			System.out.format("%d|", i);
		}
		System.out.println();

		for (int r = (numOfRows - 1); r >= 0; --r) {
			System.out.print("|");
			for (int c = 0; c < numOfCols; ++c) {
				if (grid[c][r] == 1) {
					System.out.print("Y|");
				} else if (grid[c][r] == 2) {
					System.out.print("R|");
				} else {
					System.out.print(" |");
				}
			}
			System.out.println();
		}
	}
}
