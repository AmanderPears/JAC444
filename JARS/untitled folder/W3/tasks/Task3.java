package tasks;

import java.util.Scanner;

import classes.GeometricObject;
import classes.Square;

public class Task3 {

	public Task3(Scanner in) {
		int rnd;
		boolean rndBool;

		GeometricObject[] list = new Square[5];
//check before cast
		for (GeometricObject o : list) {
			rnd = (int) (Math.random() * 10 + 1);
			rndBool = (int) (Math.random() * 2) == 1;
			o = new Square(rnd, rndBool);
			System.out.println("Area: " + o.getArea());
			if (o instanceof Square) {
				if (((Square) o).isColorable()) {
					((Square) o).howToColor();
				}
			}
		}
	}
}
