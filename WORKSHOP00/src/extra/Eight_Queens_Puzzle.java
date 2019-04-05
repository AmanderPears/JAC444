package extra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Eight_Queens_Puzzle {

	int numOfCols = 8, numOfRows = 8;
	HashMap<QPoint, Boolean> qPoints = new HashMap<QPoint, Boolean>();
	QPoint tempPoint = new QPoint(0, 0);

	void initialize() {
		// fill empty
		for (int r = 0; r < numOfRows; ++r) {
			for (int c = 0; c < numOfCols; ++c) {
				qPoints.put(new QPoint(r, c), new Boolean(false));
			}
		}
	}

	void display() {
		// display
		System.out.print("  ");
		for (int i = 0; i < numOfCols; ++i)
			System.out.print(i + " ");
		System.out.println();

		int lbl = 97;
		for (int r = 0; r < numOfRows; ++r) {
			System.out.print((char) lbl++ + " ");
			for (int c = 0; c < numOfCols; ++c) {
				tempPoint.x = r;
				tempPoint.y = c;
				System.out.print(qPoints.get(tempPoint) ? "X|" : " |");
			}
			System.out.println();
		}

	}

	QPoint generateQueen() {
		return new QPoint((int) (Math.random() * numOfCols), (int) (Math.random() * numOfRows));
	}

	boolean rowMatch(QPoint val) {
		QPoint q = new QPoint(val.x, val.y);
		for (q.y = 0; q.y < numOfRows; ++q.y) {
			if (q != val && qPoints.get(q)) {
				return true;
			}
		}
		return false;
	}

	boolean colMatch(QPoint val) {
		QPoint q = new QPoint(val.x, val.y);
		for (q.x = 0; q.x < numOfCols; ++q.x) {
			if (q != val && qPoints.get(q)) {
				return true;
			}
		}
		return false;
	}

	boolean diag1Match(QPoint val) {
		QPoint q = new QPoint(val.x, val.y);
		for (q.x = q.x, q.y = q.y; q.x < numOfCols && q.y < numOfRows; ++q.x, ++q.y) {
			if (q != val && qPoints.get(q)) {
				return true;
			}
		}
		return false;
	}

	boolean diag2Match(QPoint val) {
		QPoint q = new QPoint(val.x, val.y);
		for (q.x = q.x, q.y = q.y; q.x >= 0 && q.y >= 0; --q.x, --q.y) {
			if (q != val && qPoints.get(q)) {
				return true;
			}
		}
		return false;
	}

	boolean diag3Match(QPoint val) {
		QPoint q = new QPoint(val.x, val.y);
		for (q.x = q.x, q.y = q.y; q.x >= 0 && q.y < numOfRows; --q.x, ++q.y) {
			if (q != val && qPoints.get(q)) {
				return true;
			}
		}
		return false;
	}

	boolean diag4Match(QPoint val) {
		QPoint q = new QPoint(val.x, val.y);
		for (q.x = q.x, q.y = q.y; q.x < numOfCols && q.y >= 0; ++q.x, --q.y) {
			if (q != val && qPoints.get(q)) {
				return true;
			}
		}
		return false;
	}

	boolean match(QPoint val) {
		return diag1Match(val) || diag2Match(val) || diag3Match(val) || diag4Match(val) || colMatch(val)
				|| rowMatch(val);
	}

	public Eight_Queens_Puzzle(int val) {
		numOfCols = val;
		numOfRows = val;
		
		initialize();

		int total = 0, round = 0, count = 0;
		do {
			if (round == (numOfCols * numOfRows)) {
				initialize();
				total += round;
				round = 0;
				count = 0;
			}

			tempPoint = generateQueen();
			if (!match(tempPoint)) {
				qPoints.replace(tempPoint, true);
				count++;
			}
			round++;

		} while (count < val);
		display();
		System.out.println("total tries: " + total);

	}

	static int prompt(Scanner in) {
		int i = 0;
		while (i < 1) {
			while (!in.hasNextInt()) {
				System.out.print("Enter a valid integer: ");
				in.next();
			}
			i = in.nextInt();
		}
		return i;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter game dimensions:");
		System.out.print ("Enter number of columns and rows: ");
		int val = prompt(in);

		new Eight_Queens_Puzzle(val);

	}

}

class QPoint {
	public int x, y;
	// public boolean used;

//	public QPoint(int x, int y, boolean used) {
	public QPoint(int x, int y) {
		this.x = x;
		this.y = y;
		// this.used = used;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof QPoint)) {
			return false;
		}

		QPoint that = (QPoint) other;

		// Custom equality check here.
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {
		return ((37 + (new Integer(x)).hashCode()) * 37) + (new Integer(y)).hashCode();
	}
}
