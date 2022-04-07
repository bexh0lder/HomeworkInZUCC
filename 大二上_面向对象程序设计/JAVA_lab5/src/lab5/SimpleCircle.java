package lab5;
import java.util.Scanner;
public class SimpleCircle{
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		SimpleCircle circle1 = new SimpleCircle();
		System.out.println("The area of the circle of radius " + circle1.radius + " is " + circle1.getArea());
		
		SimpleCircle circle2 = new SimpleCircle(25);
		System.out.println("The area of the circle of radius " + circle2.radius + " is " + circle2.getArea());
		
	}
	double radius;
	SimpleCircle(){
		radius = 1;
	}
	SimpleCircle(double newRadius){
		radius = newRadius;
	}
	double getArea() {
		return radius * radius * Math.PI;
	}
	double getPerimeter() {
		return 2 * radius * Math.PI;
	}
	void setRadius(double newRadius) {
		radius = newRadius;
	}
}
