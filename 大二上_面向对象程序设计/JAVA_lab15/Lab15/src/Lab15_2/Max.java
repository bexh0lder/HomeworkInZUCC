package Lab15_2;

public class Max {
    public static ComparableCircle max(ComparableCircle o1, ComparableCircle o2) {
        if (o1.compareTo(o2) > 0) return o1;
        else return o2;
    }
}
