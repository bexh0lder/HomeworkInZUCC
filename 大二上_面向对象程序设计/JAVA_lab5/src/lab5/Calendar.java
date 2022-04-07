package lab5;
import java.util.GregorianCalendar;
public class Calendar {
	public static void main(String argus[]) {
		GregorianCalendar Calendar1 = new GregorianCalendar(2018,2,28);
		System.out.println("Year is " + Calendar1.get(GregorianCalendar.YEAR));
		System.out.println("Month is " + Calendar1.get(GregorianCalendar.MONTH));
		System.out.println("Date is " + Calendar1.get(GregorianCalendar.DATE));
		
		GregorianCalendar Calendar2 = new GregorianCalendar(1970,1,1);
		Calendar2.setTimeInMillis(1234567898765L);
		System.out.println("Year is " + Calendar2.get(GregorianCalendar.YEAR));
		System.out.println("Month is " + Calendar2.get(GregorianCalendar.MONTH));
		System.out.println("Date is " + Calendar2.get(GregorianCalendar.DATE));
	}
}

