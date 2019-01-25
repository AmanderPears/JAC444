package classes;

import java.text.DecimalFormat;

public class Location {

	private static int row = 0, column = 0;
	private static double maxValue = 0;
	private static DecimalFormat mask = new DecimalFormat("0.#");

	public Location(int r, int c, double v) {
		row = r;
		column = c;
		maxValue = v;
	}

	@Override
	public String toString() {
		return mask.format(maxValue) + " at (" + row + ", " + column + ")";
	}

	public static Location locateLargest(double[][] a) {

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] > maxValue) {
					maxValue = a[i][j];
					row = i;
					column = j;
				}
			}
		}

		return new Location(row, column, maxValue);
	}
}
