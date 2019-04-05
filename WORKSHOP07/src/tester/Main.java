package tester;

import java.util.Scanner;

public class Main {

	/*
	 * public static void main(String[] args) { // TODO Auto-generated method stub
	 * 
	 * double a[][] = getBigMat(); double b[][] = getBigMat();
	 * 
	 * //sequential addition
	 * System.out.println("Testing sequential matrix addition"); long Start =
	 * System.nanoTime();
	 * 
	 * try { sequentialAdd(a, b); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * long End = System.nanoTime(); System.out.println("Time taken: " + ((End -
	 * Start) / 1000000.0) + " ms");
	 * 
	 * //Parallel addition System.out.println("Testing parallel matrix addition");
	 * 
	 * Start = System.nanoTime(); try { parallelAdd(a, b); } catch (Exception e) {
	 * e.printStackTrace(); } End = System.nanoTime();
	 * System.out.println("Time taken: " + ((End - Start) / 1000000.0) + " ms");
	 * 
	 * }
	 */

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int option;
		do {
			// display options
			System.out.println("Welcome to my workshop 7 demo\nSelect a task to run:\n");

			System.out.println("1. Threading demo");
			System.out.println("2. Set and JavaFX");
			System.out.println("0. EXIT\n");

			// get valid input, retry until valid
			do {
				System.out.print("Please enter your option: ");
				while (!in.hasNextInt()) {
					System.out.print("Please enter your option: ");
					in.next();
				}
				option = in.nextInt();
			} while (option < 0 || option > 4);

			// action if option == 1
			char rerun = 'Y';
			switch (option) {

			case 1:
				while (rerun == 'Y') {
					System.out.println("Running task 1...\n");
					task2.MatrixAddition.main(null, in);

					System.out.print("\nEnter 'y/Y' to rerun: ");
					rerun = in.next().toUpperCase().charAt(0);
				}

				break;
			case 2:
				System.out.println("Running task 2...\n");
				task1.Main.main(null);

				System.out.print("Cannot relaunch JavaFX apps anymore\n" + "PLEASE RELAUNCH MAIN DEMO\n\n");
				option = 0;

				break;
			default:
				System.out.println("Exiting program...");
			} // end switch

		} while (option != 0); // end do-while

	}

}
