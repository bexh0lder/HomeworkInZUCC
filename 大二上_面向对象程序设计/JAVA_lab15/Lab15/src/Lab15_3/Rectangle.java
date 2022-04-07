package Lab15_3;


/*
�� Two double data fields named width and height that specify the width and height of the rectangle. 
	The default values are 1 for both width and height.
�� A no-arg constructor that creates a default rectangle.
�� A constructor that creates a rectangle with the specified width and height.
�� A method named getArea() that returns the area of this rectangle.
�� A method named getPerimeter() that returns the perimeter.
 */
class Rectangle extends GeometricObject {
	double width = 1.0;
	double height = 1.0;
	public Rectangle() {
		super();
	}
	public Rectangle(double width, double height) {
		super();
		this.width = width;
		this.height = height;
	}
	public double getArea() {
		return width * height;
	}
	
	public double getPerimeter() {
		return 2 * (width + height);
	}

	@Override
	public String toString() {
		return "Rectangle";
	}
}
