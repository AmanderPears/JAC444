package workshop01;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner get_input = new Scanner(System.in);
		int input = 0;

		while (input != 4) {
			input = 0;
			System.out.println("\n\nWelcome to my worshop 1 demonstration!");
			System.out.println("Select program to run:");
			System.out.println("1: Solve an equation");
			System.out.println("2: What is it on a particular date?");
			System.out.println("3: Amortization calculator");
			System.out.println("4: Exit this program completely\n");

			while (input < 1 || input > 4) {
				System.out.print("Please enter a valid choice (1 - 4): ");
				while (!get_input.hasNextInt()) {
					System.out.print("Please enter a valid choice (1 - 4): ");
					get_input.next();
				}

				input = get_input.nextInt();
			}

			char in = 'u';
			boolean same = true;
			while (same) {
				if (input == 1) {
					printStart(1);
					Task01.run(get_input);
					printEnd(1);
				} else if (input == 2) {
					printStart(2);
					Task02.run(get_input);
					printEnd(2);
				} else if (input == 3) {
					printStart(3);
					Task03.run(get_input);
					printEnd(3);
				} else {
					break;
				}

				System.out.print("\nPress 'y' or 'Y' to repeat previous program: ");
				in = get_input.next().toUpperCase().charAt(0);
				same = in == 'Y' ? true : false;
			}
		}

		get_input.close();
	}

	private static void printStart(int i) {
		System.out.println("=======================");
		System.out.format("***Program %d - Start***\n", i);
		System.out.println("=======================\n");
	}

	private static void printEnd(int i) {
		System.out.println("=======================");
		System.out.format("*** Program %d - End ***\n", i);
		System.out.println("=======================\n");
	}

}// end class
