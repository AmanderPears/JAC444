package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Numbers {

	public static void generateNumberCombos() throws IOException {

		System.out.println("Generating number text combos ...\n");

		// setup char codes
		final int START = 2, END = 9;
		int alphaList[][] = new int[10][];
		int charCode = 65;
		for (int i = START; i <= END; i++) {
			System.out.print(i + ": ");
			if (i != 7 && i != 9)
				alphaList[i] = new int[3];
			else
				alphaList[i] = new int[4];
			for (int j = 0; j < alphaList[i].length; j++) {
				alphaList[i][j] = charCode++;
				System.out.print((char) (alphaList[i][j]));
			}
			System.out.println();
		}
		System.out.println();

		// generate phonenumber
		int totalCombo = 1;

		final int NUM_OF_DIGITS = 7;
		int numList[] = new int[NUM_OF_DIGITS];
		System.out.print("Phone number: ");
		for (int i = 0; i < NUM_OF_DIGITS; i++) {
			numList[i] = (int) (2 + Math.random() * 8);
			System.out.print(numList[i] + " ");

			totalCombo *= alphaList[numList[i]].length;
		}
		System.out.println("\nTotal combinations: " + totalCombo);

		// try writing
		StringBuilder sb = new StringBuilder();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
			for (int one = 0; one < alphaList[numList[0]].length; one++) {
				for (int two = 0; two < alphaList[numList[1]].length; two++) {
					for (int three = 0; three < alphaList[numList[2]].length; three++) {
						for (int four = 0; four < alphaList[numList[3]].length; four++) {
							for (int five = 0; five < alphaList[numList[4]].length; five++) {
								for (int six = 0; six < alphaList[numList[5]].length; six++) {
									for (int seven = 0; seven < alphaList[numList[6]].length; seven++) {
										sb.append((char) alphaList[numList[0]][one]);
										sb.append(' ');
										sb.append((char) alphaList[numList[1]][two]);
										sb.append(' ');
										sb.append((char) alphaList[numList[2]][three]);
										sb.append(' ');
										sb.append((char) alphaList[numList[3]][four]);
										sb.append(' ');
										sb.append((char) alphaList[numList[4]][five]);
										sb.append(' ');
										sb.append((char) alphaList[numList[5]][six]);
										sb.append(' ');
										sb.append((char) alphaList[numList[6]][seven]);

										bw.write(sb.toString());
										bw.newLine();

										sb = reuseStringBuilder(sb);
									} // end seven
								} // end six
							} // end five
						} // end four
					} // end three
				} // end two
			} // end one

			bw.close();
		} // end try

		System.out.println("\nList generated and printed to file 'output.txt'.");
	}

	static StringBuilder reuseStringBuilder(final StringBuilder sb) {
		return sb.delete(0, sb.length());
	}

}
