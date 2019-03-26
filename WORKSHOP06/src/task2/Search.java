package task2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Search {
	// valid range is 2001-2010 inclusive
	static boolean checkYear(int year) {
		return year > 2000 && year < 2011;
	}

	static boolean checkGender(char gender) {
		return Character.toUpperCase(gender) == 'M' || Character.toUpperCase(gender) == 'F';
	}

	public static String searchFile(int year, char gender, String name) {
		if (checkYear(year) && checkGender(gender)) {
			// correct case part 1
			gender = Character.toUpperCase(gender);
			name = name.toLowerCase();

			String line = "resource/babynamesranking" + year + ".txt";

			try (InputStreamReader in = new InputStreamReader(Search.class.getClassLoader().getResourceAsStream(line));
					BufferedReader br = new BufferedReader(in)) {

				// String line;
				while ((line = br.readLine()) != null) {
					if (line.toLowerCase().contains(name)) {

						// System.out.println(line);
						String[] tokens = line.split("\\s+");
						if (gender == 'M' && tokens[1].compareToIgnoreCase(name) == 0) {
							line = String.format("The name ranked #%s in %d and %s boys were called %s.", tokens[0],
									year, tokens[2], tokens[1]);

						} else if (gender == 'F' && tokens[3].compareToIgnoreCase(name) == 0) {
							line = String.format("The name ranked #%s in %d and %s girls were called %s.", tokens[0],
									year, tokens[4], tokens[3]);
						} else {
							continue;
						}

						return line;
					}
				}

			} catch (Exception e) {
				System.out.println("ERROR IN SEARCH.SEARCHFILE");
				e.printStackTrace();
			}
		}

		return "Name not found, check query!";
	}
}
