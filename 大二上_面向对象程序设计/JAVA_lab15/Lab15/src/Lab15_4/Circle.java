package Lab15_4;

public class Circle extends GeometricObject {
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle";
    }

    public int compareTo(Circle obj) {
        if (this.radius > obj.radius) return 1;
        else return -1;
    }


    @Override
    public boolean equals(Object obj) {
        return ((Circle)obj).radius == this.radius;
    }
}
