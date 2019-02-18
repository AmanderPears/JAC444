package main;

import java.util.Scanner;

import tasks.*;

public class TesterClass {
	/** Main entry point for workshop tasks */
	public static void main(String[] args) {

		// Initialize input
		Scanner getInput = new Scanner(System.in);
		int choice;

		do {
			choice = 0;
			// Decorations
			// printHorizontalLine();
			System.out.println("Workshop - 3");
			printHorizontalLine();

			System.out.println("Testers for workshop 3:\n");
			System.out.println("1. Test 1 - Complex Numbers");
			System.out.println("2. Test 2 - Bank Chain");
			System.out.println("3. Test 3 - Objects");
			System.out.println("4. EXIT this program\n");

			while (choice < 1 || choice > 4) {

				System.out.print("Please enter a valid choice (1-4): ");

				while (!getInput.hasNextInt()) {
					System.out.print("Please enter a valid choice (1-4): ");
					getInput.next();
				}

				choice = getInput.nextInt();
			}

			// if 4 break and exit
			if (choice == 4)
				break;

			// run chosen task
			do {
				System.out.println();
				printHorizontalLine();
				System.out.format("Running Task %d:\n\n", choice);
				if (choice == 1)
					new Task1(getInput);
				else if (choice == 2)
					new Task2(getInput);
				else if (choice == 3)
					new Task3(getInput);
				System.out.format("\nTask %d Terminated.\n", choice);

				System.out.print("Press 'y' to repeat previous program. ");
			} while (getInput.next().toUpperCase().charAt(0) == 'Y');
			System.out.println();
			printHorizontalLine();

		} while (choice != 4);

		// Close scanner object;
		getInput.close();

		// printHorizontalLine();

	}

	/**
	 * This function does not do much. Just creates a visual divider on the display.
	 */
	private static void printHorizontalLine() {
		String s = "+";
		for (int i = 0; i < 6; i++)
			s += s;
		System.out.println(s);
	}
}
