package lab5;

import java.util.Scanner;

public class Rectangle {
	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		Rectangle tan1 = new Rectangle(4, 40);
		System.out.println("The area of a Rectangle with width " + tan1.width + " and height " + tan1.height + " is " + tan1.getArea());
		System.out.println("The perimeter of a rectangle is " + tan1.getPerimeter());
		
		Rectangle tan2 = new Rectangle(3.5, 35.9);
		System.out.println("The area of a Rectangle with width " + tan2.width + " and height " + tan2.height + " is " + tan2.getArea());
		System.out.println("The perimeter of a rectangle is " + tan2.getPerimeter());
		
	}
	double width;
	double height;
	Rectangle(){
		width = 1;
		height = 1;
	}
	Rectangle(double newwidth,double newheight){
		width = newwidth;
		height = newheight;
	}
	double getArea() {
		return width * height;
	}
	double getPerimeter() {
		return 2 * (height + width);
	}
	void setRadius(double newwidth,double newheight){
		width = newwidth;
		height = newheight;
	}
}
