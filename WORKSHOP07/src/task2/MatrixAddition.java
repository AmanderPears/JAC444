package task2;

import java.util.Scanner;

public class MatrixAddition {

	public static double[][] sequentialAdd(double[][] a, double[][] b) throws Exception {

		double[][] result = new double[a.length][a[0].length];

		// check length of entire array
		if (a.length == b.length) {

			for (int i = 0; i < a.length; ++i) {
				// check inner array length before addition
				if (a[i].length == b[i].length) {
					for (int j = 0; j < a[i].length; ++j) {
						result[i][j] = a[i][j] + b[i][j];
					}
				} else {
					throw new Exception("Matrixes need to have the same dimensions.");
				}
			}
		}

		return result;

	}

	public static double[][] parallelAdd(double[][] a, double[][] b) {
		double[][] result = new double[a.length][a[0].length];

		if (a.length == b.length) {
			int midpoint = Math.floorDiv(a.length, 2);

			// first half
			Thread t1 = new Thread() {
				public void run() {
					for (int i = 0; i < midpoint; ++i) {
						if (a[i].length == b[i].length) {
							for (int j = 0; j < a[i].length; ++j) {
								result[i][j] = a[i][j] + b[i][j];
							}
						}
					}
				}
			};

			// second half
			Thread t2 = new Thread() {
				public void run() {
					for (int i = midpoint; i < a.length; ++i) {
						if (a[i].length == b[i].length) {
							for (int j = 0; j < a[i].length; ++j) {
								result[i][j] = a[i][j] + b[i][j];
							}
						}
					}
				}
			};

			// start threads
			t1.start();
			t2.start();
		}

		return result;
	}

	static void printMatrix(double[][] dd) {
		for (double[] o : dd) {
			System.out.print("{");
			for (double i : o) {
				System.out.print(i + " ");
			}
			System.out.print("}\n");
		}
		System.out.println();
	}

	static double[][] getBigMat(int out, int in) {
		final int outer = out, inner = in;
		double[][] result = new double[outer][inner];

		Thread t1 = new Thread() {
			public void run() {
				for (int o = 0; o < (int) (outer / 2); ++o) {
					for (int i = 0; i < inner; ++i) {
						result[o][i] = Math.random() * 10;
					}
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				for (int o = (int) (outer / 2); o < outer; ++o) {
					for (int i = 0; i < inner; ++i) {
						result[o][i] = Math.random() * 10;
					}
				}
			}
		};

		t1.start();
		t2.start();

		return result;
	}

	static int prompt(Scanner in) {
		int i = 0;
		while (i < 1) {
			while (!in.hasNextInt()) {
				System.out.print("Please enter a valid integer: ");
				in.next();
			}
			i = in.nextInt();
		}
		return i;
	}

	public static void main(String[] args, Scanner in) { // TODO Auto-generated method stub

		// welcome and prompt for array dimensions
		System.out.println("Enter dimensions for the two matrices\n");

		System.out.print("Enter outer size: ");
		int outer = prompt(in);

		System.out.print("Enter inner size: ");
		int inner = prompt(in);

		// declare and initialize array with given dimensions
		double[][] a = getBigMat(outer, inner), b = getBigMat(outer, inner);

		// start sequential matrix addition
		System.out.println("\nTesting sequential matrix addition");
		long Start = System.nanoTime();

		try {
			sequentialAdd(a, b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// calculate time taken
		long End = System.nanoTime();
		double seqTime = (End - Start) / 1000000.0;
		System.out.println("Time taken: " + seqTime + " ms");

		///////////
		// start Parallel matrix addition
		System.out.println("\nTesting parallel matrix addition");

		Start = System.nanoTime();
		try {
			parallelAdd(a, b);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// calculate time taken
		End = System.nanoTime();
		double parTime = (End - Start) / 1000000.0;
		System.out.println("Time taken: " + parTime + " ms");

		// print comparison results
		System.out.format("\n============================\n");
		if ((seqTime - parTime) > 0)
			System.out.format("Threading time saved: %.2fms\n", seqTime - parTime);
		else
			System.out.format("Threading was slower, took additional: %.2fms\n", parTime - seqTime);
		System.out.format("============================\n");

		// clear arrays
		a = null;
		b = null;

	}

}
