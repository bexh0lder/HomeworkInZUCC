package Lab15_1;

class Rectangle extends GeometricObject{
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
