import java.util.Scanner;
public class find {
    public static void main(String argus[]){
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer: ");

        int number = input.nextInt();

        System.out.println("The factors for "+ number + " is ");

        int x = 2;
        while(number != 1){
            if(number % x == 0){
                System.out.print(x + " ");
                number /= x;
                x = 1;
            }
            x++;
        }
    }
}
