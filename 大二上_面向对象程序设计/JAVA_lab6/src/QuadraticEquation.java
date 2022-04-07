import java.util.Scanner;
public class QuadraticEquation {
    public static void main(String[] args){
        QuadraticEquation q1 = new QuadraticEquation();
        q1.getABC();
        if(q1.getDiscriminant() > 0)
            System.out.println("The roots are " + q1.getRoot1() + " and " + q1.getRoot2());
        else if(q1.getDiscriminant() == 0)
            System.out.println("The roots is " + q1.getRoot1());
        else
            System.out.println("The equation has no roots");
        QuadraticEquation q2 = new QuadraticEquation();
        q2.getABC();
        if(q2.getDiscriminant() > 0)
            System.out.println("The roots are " + q2.getRoot1() + " and " + q2.getRoot2());
        else if(q2.getDiscriminant() == 0)
            System.out.println("The roots is " + q2.getRoot1());
        else
            System.out.println("The equation has no roots");
    }
    private double a, b, c;
    double getDiscriminant(){
        return Math.pow(b, 2) - 4 * a * c;
    }
    double getRoot1(){
        return  (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
    }
    double getRoot2(){
        return  (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
    }
    void getABC(){
        System.out.print("Enter a, b, c: ");
        Scanner input = new Scanner(System.in);
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();
    }
}
