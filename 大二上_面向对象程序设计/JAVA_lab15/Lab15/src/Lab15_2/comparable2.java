package Lab15_2;

public class comparable2{
    public static void main(String argus[]){
        ComparableCircle circle1 = new ComparableCircle(5);
        ComparableCircle circle2 = new ComparableCircle(15);

        ComparableCircle circle3 = (ComparableCircle)Max.max(circle1, circle2);
        System.out.println("The max circle's radius is " + circle3.getRadius());
    }
}
