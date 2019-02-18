package tasks;

import java.util.Scanner;
import classes.Banks;

public class Task2 {

	public Task2(Scanner in) {

		// new bank list
		Banks banks = new Banks(getInt(in, "Number of banks: "), getDouble(in, "Minimum asset limit: "));

		for (int i = 0; i < Banks.numOfBanks; i++) {
			banks.addBank(getInt(in, "Enter bank Id: "), getDouble(in, "-> Enter bank balance: "));
			banks.bankList[i].setBanksLoaned(getInt(in, "-> Number of loans: "));
			for (int j = 0; j < banks.bankList[i].banksLoaned; j++) {
				banks.bankList[i].addLoan(getInt(in, "-> Bank Id: "), getDouble(in, "-> Amount: "));
			}
		}

		System.out.print("IDs of unsafe banks: ");
		for (Integer i : banks.unsafeList()) {
			if (i > -1)
				System.out.print(i + " ");
		}
		System.out.println();
	}

	int getInt(Scanner in, String s) {
		int n = -1;
		while (n < 0) {
			System.out.print(s);
			while (!in.hasNextInt()) {
				System.out.print(s);
				in.next();
			}
			n = in.nextInt();
		}
		return n;
	}

	double getDouble(Scanner in, String s) {
		double n = -1;
		while (n < 0) {
			System.out.print(s);
			while (!in.hasNextDouble()) {
				System.out.print(s);
				in.next();
			}
			n = in.nextDouble();
		}
		return n;
	}
}
