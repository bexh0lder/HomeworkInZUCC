import java.util.*;

public class Exercise {
    public static void main(String[] argus) {
        Scanner input = new Scanner(System.in);
        ArrayList <Integer> l1 = new ArrayList <Integer>();
        System.out.print("Enter intergers (input ends with 0): ");
        while(true) {
            int x = input.nextInt();
            if(x == 0) break;
            l1.add(x);
        }
        System.out.println("The minimum number is " + min(l1));
    }
    public static Integer min(ArrayList <Integer> list) {
        Collections.sort(list);
        return list.get(0);
    }
}
