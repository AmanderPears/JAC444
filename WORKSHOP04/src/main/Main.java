package main;

import java.io.IOException;
import java.util.Scanner;

import core.Hangman;
import core.LetterCount;
import core.Numbers;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int option;
		do {
			// display options
			System.out.println("Welcome to my workshop 4 demo\nSelect a task to run:\n");

			System.out.println("1. Hangman");
			System.out.println("2. Alphabet Count");
			System.out.println("3. Tele number to Text");
			System.out.println("0. EXIT\n");

			// get valid input, retry until valid
			do {
				System.out.print("Please enter your option: ");
				while (!in.hasNextInt()) {
					System.out.print("Please enter your option: ");
					in.next();
				}
				option = in.nextInt();
			} while (option < 0 || option > 3);

			// action if option == 1
			char rerun = 'Y';
			switch (option) {

			case 1:
				while (rerun == 'Y') {
					System.out.println("Running task 1...\n");
					new Hangman(in);

					System.out.print("\nEnter 'y/Y' to rerun: ");
					rerun = in.next().toUpperCase().charAt(0);
				}

				break;
			case 2:
				while (rerun == 'Y') {
					System.out.println("Running task 2...\n");
					try {
						new LetterCount(in);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.print("Enter 'y/Y' to rerun: ");
					rerun = in.next().toUpperCase().charAt(0);
				}

				break;
			case 3:
				while (rerun == 'Y') {
					System.out.println("Running task 2...\n");
					try {
						Numbers.generateNumberCombos(in);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.print("Enter 'y/Y' to rerun: ");
					rerun = in.next().toUpperCase().charAt(0);
				}

				break;
			default:
				System.out.println("Exiting program...");
			} // end switch

		} while (option != 0); // end do-while

	}// end main

} // end class
