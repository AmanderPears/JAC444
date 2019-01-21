//Amander Pears
//051117133
package workshop01;

import java.util.Scanner;

public class Task02 {

	public static void run(Scanner get_input) {
		int year = get_var("Enter year: (e.g)., 2012): ", get_input, 1582, 9999);

		int month = get_var("Enter month: 1-12:", get_input, 1, 12);

		int feb = 28;
		if (year % 4 != 0)
			feb = 28;
		else if (year % 100 != 0)
			feb = 29;
		else if (year % 400 != 0)
			feb = 28;
		else
			feb = 29;

		int[] daysInMonth = { 0, 31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int day = get_var("Enter the day of the month: 1-31: ", get_input, 1, daysInMonth[month]);

		if (month == 1) {
			month = 13;
			year--;
		} else if (month == 2) {
			month = 14;
			year--;
		}

		String[] days = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
		int h = (day + ((26 * (month + 1)) / 10) + (year % 100) + ((year % 100) / 4) + ((year / 100) / 4)
				+ (5 * (year / 100))) % 7;
		System.out.format("\nDay of the week is %s\n", days[h]);
	}

	public static int get_var(String text, Scanner get_input, int min, int max) {
		int var;
		do {
			System.out.print(text);
			while (!get_input.hasNextInt()) {
				System.out.print("Please enter a valid input: ");
				get_input.next();
			}
			var = get_input.nextInt();
		} while (var < min || var > max);

		return var;
	}
}