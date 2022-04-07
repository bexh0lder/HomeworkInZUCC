package Lab15_4;

public class CircleTest {
    public static void main(String[] args) {
        Circle obj1 = new Circle(1);
        Circle obj2 = new Circle(2);

        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.compareTo(obj2));
    }
}
