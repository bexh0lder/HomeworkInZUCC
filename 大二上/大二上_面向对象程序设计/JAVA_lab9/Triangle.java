import java.util.*;
public class Triangle extends GeometricObject{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Enter three sides: ");
        double side1 = input.nextDouble();
        double side2 = input.nextDouble();
        double side3 = input.nextDouble();

        System.out.println("Enter the color: ");
        String color = input.next();

        System.out.println("Enter a boolean value for filled: ");
        boolean filled = input.nextBoolean();

        Triangle t1 = new Triangle(side1, side2, side3);
        t1.color = color;
        t1.filled = filled;
        System.out.println(t1.toString());
        System.out.println("The area is " + t1.getArea());
        System.out.println("The perimeter is " + t1.getPerimeter());
        System.out.println("The color is " + t1.color);
        System.out.println("Is it filled? " + t1.filled);
    }
    public double side1;
    public double side2;
    public double side3;

    public Triangle(){
        this.side1 = 1.0;
        this.side2 = 1.0;
        this.side3 = 1.0;
    }
    public Triangle(double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    public double getSide1(){
        return side1;
    }
    public double getSide2(){
        return side2;
    }
    public double getSide3(){
        return side3;
    }
    public double getArea(){
        double s = (side1 + side2 + side3) / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }
    public double getPerimeter(){
        return side1 + side2 + side3;
    }
    public String toString(){
        return "Triangle: side1 = " + side1 + " side2 = " + side2 +" side3 = " + side3;
    }
}
class GeometricObject{
    public String color;
    public boolean filled;
    public java.util.Date dateCreated;

    public GeometricObject(){
        dateCreated = new java.util.Date();
    }
    public GeometricObject(String color, boolean filled){
        dateCreated = new java.util.Date();
        this.color = color;
        this.filled = filled;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String newColor){
        this.color = newColor;
    }
    public boolean isFilled(){
        return filled;
    }
    public void setFilled(boolean newFilled){
        this.filled = newFilled;
    }
    public java.util.Date getDateCreated() {
        return dateCreated;
    }
    public String toString() {
        return "The color is " + color + "\n" + "Is it filled? " + filled;
    }
}

