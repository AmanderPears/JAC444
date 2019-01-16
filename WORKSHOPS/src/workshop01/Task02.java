//Amander Pears
//051117133
package workshop01;

import java.util.Scanner;

public class Task02 {

	public static void run() {
		Scanner getInput = new Scanner(System.in);

		System.out.print("Enter year: (e.g)., 2012): ");
		int year = getInput.nextInt();

		System.out.print("Enter month: 1-12:" );
		int month = getInput.nextInt();
		
		if (month == 1) {
			month = 13;
			year--;
		}
		else if (month == 2) {
			month = 14;
			year--;
		}

		System.out.print("Enter the day of the month: 1-31: ");
		int day = getInput.nextInt();

		getInput.close();

		String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
		int h = (day+((26*(month+1))/10)+(year%100)+((year%100)/4)+((year/100)/4)+(5*(year/100)))%7;			
		System.out.format("Day of the week is %s\n\n", days[h]);
	}
}