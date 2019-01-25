package tasks;

import java.util.Scanner;
import classes.CreditCardNumber;

public class Task3 {

	public Task3(Scanner in) {

		System.out.println("Task - 3\n");

		System.out.println("1. Validate card number");
		System.out.println("2. Create card number\n");

		int n = 0;
		while (n < 1 || n > 2) {
			n = 0;
			System.out.print("Please enter choice: ");
			while (!in.hasNextInt()) {
				System.out.print("Please enter choice: ");
				in.next();
			}
			n = in.nextInt();
		}
		System.out.println();
		if (n == 1) {
			long ln = 0L;
			System.out.print("Please enter card number: ");
			while (!in.hasNextLong()) {
				System.out.print("Please enter card number: ");
				in.next();
			}
			ln = in.nextLong();

			if (CreditCardNumber.isValid(ln)) {
				System.out.format("%d is a valid number\n", ln);
			} else {
				System.out.format("%d is not a valid number\n", ln);
			}

		} else {
			System.out.println("Select type of card\n");
			System.out.println("1. VISA");
			System.out.println("2. MASTERCARD");
			System.out.println("3. DISCOVER CARD");
			System.out.println("4. AMERICAN EXPRESS\n");

			int t = 0;
			while (t < 1 || t > 4) {
				t = 0;
				System.out.print("Please enter card type: ");
				while (!in.hasNextInt()) {
					System.out.print("Please enter card type: ");
					in.next();
				}
				t = in.nextInt();
			}

			String name;
			long num;
			if (t == 1) {
				name = "VISA";
				num = CreditCardNumber.getNewCardNumber(CreditCardNumber.VISA);
			} else if (t == 2) {
				name = "MasterCard";
				num = CreditCardNumber.getNewCardNumber(CreditCardNumber.MASTER);
			} else if (t == 3) {
				name = "Discover";
				num = CreditCardNumber.getNewCardNumber(CreditCardNumber.DISCOVER);
			} else {
				name = "American Express";
				num = CreditCardNumber.getNewCardNumber(CreditCardNumber.AMERICAN);
			}

			System.out.format("New %s card number: %d\n", name, num);

		}

		// end
	}

}
