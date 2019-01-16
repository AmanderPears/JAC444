//Amander Pears
//051117133
package workshop01;

import java.util.Scanner;

public class Task01 {

	public static void run() {
		System.out.print("Enter a, b, c, d, e, f: ");
		Scanner getInput = new Scanner(System.in);
		double a = getInput.nextDouble(),
				   b = getInput.nextDouble(),
				   c = getInput.nextDouble(),
				   d = getInput.nextDouble(),
				   e = getInput.nextDouble(),
				   f = getInput.nextDouble();
		getInput.close();
		
		if ( (a*d - b*c) == 0) {
			System.out.println("This equation has no solution");
		} else {
			double x = (e*d - b*f)/(a*d - b*c),
					y = (a*f - e*c)/(a*d - b*c);
			System.out.format("x is %.1f and y is %.1f\n\n", x, y);
		}
	}
	
}
