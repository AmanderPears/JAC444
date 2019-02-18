package core;

import java.io.FileReader;

public class LetterCount {
	private static int alphabetCount[] = new int[26];

	public static FileReader fr;

	public static void readFile(final String fileName) throws Exception {
		fr = new FileReader(fileName);

		int i;
		while ((i = fr.read()) != -1)
			if (i >= 65 && i <= 90) {
				// System.out.print((char) i);

				alphabetCount[i - 65]++;
			}
		fr.close();

		printResults();
	}

	public static void printResults() {
		System.out.println("File read: output.txt");

		for (int i = 0; i < alphabetCount.length; i++) {
//			if (alphabetCount[i] > 0)
			System.out.format("Number of %c's: %d\n", (char) (i + 65), alphabetCount[i]);
		}
	}

}
