package noUI;

import java.text.DecimalFormat;
import java.util.Scanner;

class Location {

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

public class Task02 {

	public Task02(Scanner getInput) {

		System.out.print("Enter the number of rows and columns in the array: ");
		int row = getInput.nextInt(), column = getInput.nextInt();

		double[][] twoDimArray = new double[row][column];

		System.out.println("Enter the array:");
		for (int i = 0; i < twoDimArray.length; i++) {
			for (int j = 0; j < twoDimArray[i].length; j++) {
				twoDimArray[i][j] = getInput.nextDouble();
			}
		}

		System.out.format("The location of the largest element is %s\n", Location.locateLargest(twoDimArray));

	}

}
