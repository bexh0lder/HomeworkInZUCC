
public class Test {
	public static void main(String[] argus) {
		Object circle1 = new Circle();
		Object circle2 = new Circle();
		System.out.println(circle1.equals(circle2));
	}
}
//class Circle{
//	double radius;
//	
//	public boolean equals(Circle circle) {
//		return this.radius == circle.radius;
//	}
//}
class Circle{
	double radius;
	
	public boolean equals(Object circle) {
		return this.radius == ((Circle)circle).radius;
	}
}