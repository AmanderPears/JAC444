package task2;

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

//			try {
//				t1.join();
//				t2.join();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
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

	static double[][] getBigMat() {
		final int outer = 2000, inner = 2000;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double a[][] = getBigMat();
		double b[][] = getBigMat();

		System.out.print("a: ");
		// printMatrix(a);
		System.out.print("b: ");
		// printMatrix(b);

		///////////////////
		System.out.println("Testing sequential matrix addition");
		// System.out.print("a+b= ");
		long Start = System.nanoTime();

		try {
			// printMatrix(sequentialAdd(a, b));
			sequentialAdd(a, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long End = System.nanoTime();
		System.out.println("Time taken: " + ((End - Start) / 1000000.0) + " ms");

		/////////////
		System.out.println("Testing parallel matrix addition");
		// System.out.print("a+b= ");

		Start = System.nanoTime();
		try {
			// printMatrix(parallelAdd(a, b));
			parallelAdd(a, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		End = System.nanoTime();
		System.out.println("Time taken: " + ((End - Start) / 1000000.0) + " ms");

	}

}
