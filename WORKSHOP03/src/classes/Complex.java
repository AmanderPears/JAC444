package classes;

//import java.text.DecimalFormat;

public class Complex implements Cloneable {

	private double a, b;

	public Complex() {
		this(0, 0);
	}

	public Complex(double a) {
		this(a, 0);
	}

	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public void add(Complex n) {
		a += n.a;
		b += n.b;
	}

	public void subtract(Complex n) {
		a -= n.a;
		b -= n.b;
	}

	public void multiply(Complex n) {
		double x = a * n.a - b * n.b, y = b * n.a + a * n.b;
		a = x;
		b = y;
	}

	public void divide(Complex n) {
		double x = (a * n.a + b * n.b) / (n.a * n.a + n.b * n.b);
		double y = (b * n.a - a * n.b) / (n.a * n.a + n.b * n.b);
		if (Double.isNaN(x) || Double.isNaN(y)) {
			throw new RuntimeException("*** Error: Division by zero ***");
		}
		a = x;
		b = y;
	}

	public double abs() {
		return Math.sqrt(a * a + b * b);
	}

	public double getRealPart() {
		return a;
	}

	public double getImaginaryPart() {
		return b;
	}

//	public static Complex add(Complex x, Complex y) {
//		return new Complex(x.a + y.a, x.b + y.b);
//	}
//
//	public static Complex subtract(Complex x, Complex y) {
//		return new Complex(x.a - y.a, x.b - y.b);
//	}
//
//	public static Complex multiply(Complex x, Complex y) {
//		return new Complex(x.a * y.a - x.b * y.b, x.b * y.a + x.a * y.b);
//	}
//
//	public static Complex divide(Complex x, Complex y) {
//		double a = (x.a * y.a + x.b * y.b) / (y.a * y.a + y.b * y.b),
//				b = (x.b * y.a - x.a * y.b) / (y.a * y.a + y.b * y.b);
//		return new Complex(a, b);
//	}

	@Override
	public String toString() {
//		DecimalFormat f = new DecimalFormat("0.0###");
//		return f.format(a) + (b != 0 ? (" + " + f.format(b) + "i") : "");
		return a + (b != 0 ? (" + " + b + "i") : "");
	}

	@Override
	public Complex clone() {
		return new Complex(a, b);
	}
}
