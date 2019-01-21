package workshop01;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Task03 {

	public static void run(Scanner get_input) {
		double principal = get_var("Loan Amount: ", get_input, 0, Integer.MAX_VALUE);

		int period = (int) get_var("Number of Years: ", get_input, 0, Integer.MAX_VALUE);

		double rate = get_var("Annual Interest Rate: ", get_input, 0, 100);

		DecimalFormat money = new DecimalFormat("$###,###,#00.00");

		// convert rate to monthly
		rate = (rate / 100) / 12;
		// convert period to months
		period *= 12;

		double monthly = principal * ((rate * Math.pow(1 + rate, period)) / ((Math.pow(1 + rate, period)) - 1));

		System.out.format("\nMontly Payment: %15s\n", money.format(monthly));

		System.out.format("Total Payment : %15s\n\n", money.format(monthly * period));

		System.out.format("%15s%15s%15s%15s\n", "Payment#", "Interest", "Principal", "Balance");

		String a, b, c;
		for (int i = 1; i <= period; i++) {
			a = money.format(rate * principal);
			b = money.format(monthly - (rate * principal));
			principal -= (monthly - (rate * principal));
			c = money.format(principal);
			System.out.format("%15d%15s%15s%15s\n", i, a, b, c);
		}

		System.out.println();
	}

	public static double get_var(String text, Scanner get_input, int min, int max) {
		double var;
		do {
			System.out.print(text);

			while (!get_input.hasNextDouble()) {
				System.out.print(text);
				get_input.next();
			}

			var = get_input.nextDouble();

		} while (var <= min || var > max);

		return var;
	}
}
