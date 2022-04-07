package Lab15_3;

public class ColorableTest {
    public static void main(String[] args) {
        GeometricObject[] objects = {new Square(2), new Circle(5), new Square(5), new Rectangle(3,4), new Square(4.5)};

        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Colorable) {
                ((Colorable)objects[i]).howToColor();
            }else {
                System.out.println(objects[i].toString() + " can not be colored");
            }

        }
    }
}
