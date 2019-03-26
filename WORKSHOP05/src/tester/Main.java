package tester;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import connect_four.ConnectFourGame;
import task1.Account;

public class Main {

	static void runTask1PartA() {
		// clear object array and initialize array
		int rndID, rndPIN;
		Account[] acctList = new Account[11];
		for (int i = 0; i < 10; i++) {
			rndID = (int) (Math.random() * Math.pow(10, 7));
			rndPIN = (int) (Math.random() * Math.pow(10, 4));
			acctList[i] = new Account(rndID, rndPIN, 100);
			acctList[i].set_fName("Jane");
			acctList[i].set_lName("Doe");
		}
		// last object is null/end of file
		acctList[10] = null;

		// write to file
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("account.dat"))) {
			for (int i = 0; i < 10; i++) {
				out.writeObject(acctList[i]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// read from file
		Account temp;
		try {
			FileInputStream file = new FileInputStream("account.dat");
			ObjectInputStream in = new ObjectInputStream(file);

			boolean eof = false;
			while (!eof) {
				try {
					temp = (Account) in.readObject();
					System.out.println(temp);
				} catch (Exception e) {
					eof = true;
				}
			}

			in.close();
			file.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int option;
		do {
			// display options
			System.out.println("Welcome to my workshop 5 demo\nSelect a task to run:\n");

			System.out.println("1. Bank - Generate Accounts");
			System.out.println("2. Bank UI");
			System.out.println("3. Address Book");
			System.out.println("4. Connect four");
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
					runTask1PartA();

					System.out.print("\nEnter 'y/Y' to rerun: ");
					rerun = in.next().toUpperCase().charAt(0);
				}

				break;
			case 2:
				System.out.println("Running task 2...\n");
				task1.Main.main(args);

				System.out.print("Cannot relauncg JavaFX apps anymore\n" + "RELAUNCH MAIN DEMO\n\n");
				option = 0;

				break;
			case 3:
				System.out.println("Running task 3...\n");
				task2.Main.main(args);

				System.out.print("Cannot relauncg JavaFX apps anymore\n" + "RELAUNCH MAIN DEMO\n\n");
				option = 0;

				break;
			case 4:
				while (rerun == 'Y') {
					System.out.println("Running task 4...\n");
					new ConnectFourGame(in);

					System.out.print("Enter 'y/Y' to rerun: ");
					rerun = in.next().toUpperCase().charAt(0);
				}

				break;
			default:
				System.out.println("Exiting program...");
			} // end switch

		} while (option != 0); // end do-while

	}

}
