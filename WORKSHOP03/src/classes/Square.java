package classes;

public class Square extends GeometricObject implements Colorable {

	boolean colorable;

	public Square(double l, boolean c) {
		super(l, l);
		colorable = c;
	}

	public Square() {
		this(0, false);
	}

	@Override
	public void howToColor() {
		System.out.println("Color all four sides.");
	}

	@Override
	public boolean isColorable() {
		return colorable;
	}

}
