package tasks;

import java.util.Scanner;

import classes.Complex;

public class Task1 {
	public Task1(Scanner in) {

		System.out.print("Enter the first complex number: ");
		double a = getInput(in), b = getInput(in);
		Complex x = new Complex(a, b);

		System.out.print("Enter the second complex number: ");
		a = getInput(in);
		b = getInput(in);
		Complex y = new Complex(a, b), result = x.clone();

		result.add(y);
		System.out.format("(%s) + (%s) = %s\n", x, y, result);

		result = x.clone();
		result.subtract(y);
		System.out.format("(%s) - (%s) = %s\n", x, y, result);

		result = x.clone();
		result.multiply(y);
		System.out.format("(%s) * (%s) = %s\n", x, y, result);

		result = x.clone();
		try {
			result.divide(y);
			System.out.format("(%s) / (%s) = %.4f + %.1fi\n", x, y, result.getRealPart(), result.getImaginaryPart());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.format("|(%s)| = %s\n", x, x.abs());
	}

	private double getInput(Scanner in) {

		while (!in.hasNextDouble()) {
			in.next();
		}

		return in.nextDouble();
	}
}
