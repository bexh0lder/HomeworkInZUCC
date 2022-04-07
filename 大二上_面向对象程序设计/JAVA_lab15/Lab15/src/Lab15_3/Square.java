package Lab15_3;

public class Square extends GeometricObject implements Colorable{
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public void howToColor() {
        System.out.println("Square can be colored all four sides");
    }

    @Override
    public double getArea() {
        return side*side;
    }

    @Override
    public double getPerimeter() {
        return 4*side;
    }
}
