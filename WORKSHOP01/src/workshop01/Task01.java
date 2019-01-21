//Amander Pears
//051117133
package workshop01;

import java.util.Scanner;

public class Task01 {

	public static void run(Scanner get_input) {
		double a = 0, b = 0, c = 0, d = 0, e = 0, f = 0;

		a = get_var("a", get_input);
		b = get_var("b", get_input);
		c = get_var("c", get_input);
		d = get_var("d", get_input);
		e = get_var("e", get_input);
		f = get_var("f", get_input);

		if ((a * d - b * c) == 0) {
			System.out.println("\nThis equation has no solution");
		} else {
			double x = (e * d - b * f) / (a * d - b * c), y = (a * f - e * c) / (a * d - b * c);
			System.out.format("\nx is %.1f and y is %.1f\n", x, y);
		}
	}

	public static double get_var(String text, Scanner get_input) {

		System.out.print("Enter value for " + text + ": ");
		while (!get_input.hasNextDouble()) {
			System.out.print("Please enter a valid value for " + text + ": ");
			get_input.next();
		}
		return get_input.nextDouble();
	}

}
