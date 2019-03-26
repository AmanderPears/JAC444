package core;

import java.io.FileReader;
import java.util.Scanner;

public class LetterCount {
	// this static array declares 26 integers to hold total
	// occurrences of each alphabets in their respective index
	private static int alphabetCount[] = new int[26];

	public LetterCount(Scanner in) {
		// get file
		System.out.print("Enter file to be read(same directory): ");
		readFile(in.next());
	}

	public void readFile(final String file) {

		try (FileReader fr = new FileReader(file)) {
			int i;
			while ((i = Character.toUpperCase(fr.read())) != -1)
				if (i >= 65 && i <= 90) {
					// System.out.print((char) i);

					alphabetCount[i - 65]++;
				}
			fr.close();

			printResults();

		} catch (Exception e) {
			System.out.println("******************ERROR*****************");
			System.out.println(e);
			System.out.println("File could not be opened");
			System.out.println("****************************************");
		}
	}

	public static void printResults() {
		System.out.println("File read: hangman.txt");

		for (int i = 0; i < alphabetCount.length; i++) {
//			if (alphabetCount[i] > 0)
			System.out.format("Number of %c's: %d\n", (char) (i + 65), alphabetCount[i]);
		}
	}

}
