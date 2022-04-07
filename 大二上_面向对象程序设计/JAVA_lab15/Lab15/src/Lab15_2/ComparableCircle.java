package Lab15_2;

import Lab15_1.Circle;

public class ComparableCircle extends Circle implements Comparable{
    public ComparableCircle(double radius) {
        super(radius);
    }

    @Override
    public int compareTo(Object o) {
        Circle c = (Circle)o;
        return compareTo(this.getArea() > c.getArea());
    }

}
