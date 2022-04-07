package lab5;
import java.util.Random;
public class Ran {
	public static void main(String args[]) {
		Random ran = new Random();
		for(int i = 1; i <= 50; i++) {
			System.out.print(ran.nextInt(100) + " ");
			if(i % 10 == 0) System.out.println();
		}
	}
}
