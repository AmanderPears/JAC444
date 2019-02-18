package classes;

public abstract class GeometricObject {

	private double length, width;

	public GeometricObject() {
		this(0, 0);
	}

	public GeometricObject(double l, double w) {
		length = l;
		width = w;
	}

	public double getArea() {
		return length * width;
	}

}
