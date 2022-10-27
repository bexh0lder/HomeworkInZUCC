package lab5;
import java.util.Date;
public class datatime {
	public static void main(String args[]) {
		Date now = new Date();
		now.setTime(10000);
		System.out.println(now);
		now.setTime(100000);
		System.out.println(now);
		now.setTime(1000000);
		System.out.println(now);
		now.setTime(10000000);
		System.out.println(now);
		now.setTime(100000000);
		System.out.println(now);
		now.setTime(1000000000);
		System.out.println(now);
		now.setTime(10000000000L);
		System.out.println(now);
		now.setTime(100000000000L);
		System.out.println(now);
	}
}
