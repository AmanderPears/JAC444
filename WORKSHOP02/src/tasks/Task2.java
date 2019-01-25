package tasks;

import java.util.Scanner;
import classes.Location;

public class Task2 {

	public Task2(Scanner getInput) {

		System.out.print("Enter the number of rows and columns in the array: ");
		// int row = getInput.nextInt(), column = getInput.nextInt();
		int row = (int) getDouble(getInput), column = (int) getDouble(getInput);

		double[][] twoDimArray = new double[row][column];

		System.out.println("Enter the array:");
		for (int i = 0; i < twoDimArray.length; i++) {
			for (int j = 0; j < twoDimArray[i].length; j++) {
				twoDimArray[i][j] = getDouble(getInput);
			}
		}

		System.out.format("The location of the largest element is %s\n", Location.locateLargest(twoDimArray));

	}

	private double getDouble(Scanner in) {

		while (!in.hasNextDouble()) {
			in.next();
		}

		return in.nextDouble();
	}

}
