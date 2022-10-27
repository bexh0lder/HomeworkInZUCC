import java.util.ArrayList;

public class ArrayListAverage {
    public static void main(String[] args) {
        ArrayList<Number> arrayList = new ArrayList<>();
        Average(arrayList, (int) (Math.random() * 101));

    }

    public static void Average(ArrayList<Number> list, int num) {
        double sum = 0;
        for (int i = 0; i < num; i++) {
            list.add((int) (Math.random() * 101));
            sum += list.get(i).intValue();
            System.out.print(list.get(i).intValue() + " ");
            if (i % 10 == 0) System.out.println();
        }
        System.out.println();
        System.out.println(sum / num);
    }
}
