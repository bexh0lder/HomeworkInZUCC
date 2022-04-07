import java.util.ArrayList;
import java.util.Scanner;
import java.math.*;
public class SortArrayList {
    public static void main(String argus[]) {
        Scanner input = new Scanner(System.in);
        ArrayList<Number> list = new ArrayList<>();
        for(int i = 0; i < 5; ++i) {
            Number x = input.nextInt();
            list.add(x);
        }
        sort(list);
        for(Number x:list) {
            System.out.print(x.intValue() + " ");
        }
    }
    public static void sort(ArrayList<Number> list){
        for(int i = 0; i < list.size(); ++i) {
            int minIndex = i;
            Number min = list.get(minIndex);
            for(int j = i + 1; j < list.size(); ++j) {
                if(min.intValue() > list.get(j).intValue()) {
                    minIndex = j;
                    min = list.get(j);
                }
            }
            list.set(minIndex, list.get(i));
            list.set(i, min);
        }
    }
}
