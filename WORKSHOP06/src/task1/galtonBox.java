package task1;

import java.util.Scanner;

public class galtonBox {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		run(in);
		in.close();
	}

	public static int prompt(String s, Scanner in) {
		int i = -1;
		while (i < 1) {
			System.out.print(s);
			while (!in.hasNextInt()) {
				System.out.print(s);
				in.next();
			}
			i = in.nextInt();
		}
		return i;
	}

	public static void run(Scanner in) {
		final int BALLS = prompt("Enter number of balls to drop: ", in);
		final int SLOTS = prompt("Enter number of slots at the end: ", in) - 1;

		System.out.println();

		int initial, fifty = 0;
		int[] slot = new int[SLOTS + 1];

		System.out.println("Ball paths:");
		for (int j = 0; j < BALLS; ++j) {
			initial = SLOTS;
			System.out.format("%3d: ", (j + 1));
			for (int i = 0; i < SLOTS; ++i) {
				fifty = (int) (Math.random() * 2);
//				initial = fifty == 0 ? initial - 1 : initial + 1;
//				System.out.print(fifty == 0 ? 'L' : 'R');

				if (fifty == 0) {
					initial -= 1;
					System.out.print('L');
				} else {
					initial += 1;
					System.out.print('R');
				}
			}
			slot[initial / 2]++;
			System.out.format(" - slot#%2d\n", (initial / 2) + 1);
		}

		System.out.println();

//		for (int i = 0; i < SLOTS + 1; ++i) {
//			System.out.print(i);
//			for (int j = 0; j < slot[i]; ++j) {
//				System.out.print("0");
//			}
//			System.out.println();
//		}

//		for (int i = 0; i <= SLOTS; ++i) {
//			System.out.println(slot[i]);
//		}

//		// slot number
//		for (int i = 0; i <= SLOTS; ++i) {
//			System.out.print((i + 1) + "|");
//		}
//		System.out.println();

		for (int j = BALLS; j > 0; --j) {
			System.out.print("|");
			for (int i = 0; i < SLOTS + 1; ++i) {
				if (slot[i] == j) {
					System.out.print("0|");
					slot[i]--;
				} else {
					System.out.print(" |");
				}
			}
			System.out.println();
		}

	}

}
