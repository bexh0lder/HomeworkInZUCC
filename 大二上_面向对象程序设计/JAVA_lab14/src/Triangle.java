import java.util.*;
public class Triangle extends GeometricObject{
    public static void main(String argus[]) {
        Scanner input = new Scanner(System.in);
        Triangle x = new Triangle();
        System.out.println("Please input triangle's color: ");
        x.color = input.nextLine();
        System.out.println("Please input triangle's three sides: ");
        x.side1 = input.nextInt();
        x.side2 = input.nextInt();
        x.side3 = input.nextInt();
        System.out.println("Is the triangle filled?true or false?");
        x.filled = input.nextBoolean();
        if(!x.judgeTriangle()) {
            System.out.println("The three sides can not build a triangle!");
        }
        else {
            System.out.println(x.toString());
        }
    }
    double side1;
    double side2;
    double side3;
    Triangle(){};
    Triangle(double side1, double side2, double side3){
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }
    double getArea() {
        double p = (this.side1 + this.side2 + this.side3) / 2;
        return Math.sqrt(p * (p - this.side1) * (p - this.side2) * (p - this.side3));
    }
    double getPerimeter() {
        return this.side1 + this.side2 + this.side3;
    }
    boolean judgeTriangle() {
        if(side1 + side2 < side3) return false;
        if(side3 + side2 < side1) return false;
        if(side1 + side3 < side2) return false;
        return true;
    }
}
abstract class GeometricObject{
    String color;
    boolean filled;
    java.util.Date dateCreated;
    GeometricObject(){};
    GeometricObject(String color, boolean filled){
        this.color = color;
        this.filled = filled;
    }
    String getColor() {
        return this.color;
    }
    void setColor(String color) {
        this.color = color;
    }
    boolean isFilled() {
        return this.filled;
    }
    void setFilled(boolean filled) {
        this.filled = filled;
    }
    java.util.Date getDateCreated(){
        return this.dateCreated;
    }
    abstract double getArea();
    abstract double getPerimeter();
    public String toString() {
        return  "The triangle's area is " + this.getArea() +
                "\nThe triangle's perimeter is " + this.getPerimeter() +
                "\nThe triangle's color is " + this.getColor() +
                "\nIs the triangle filled? " + this.isFilled();
    }
}
