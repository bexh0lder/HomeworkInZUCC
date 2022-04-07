import java.util.Arrays;
import java.util.*;
public class StopWatch {
	public static void main(String[] args){
		int[] num = new int[100000];
		StopWatch x = new StopWatch();
		for(int i = 0; i < 100000; i++) {
			num[i] = (int)(Math.random()*100000);
		}
		x.start();
		Arrays.sort(num);
		x.end();
		System.out.print("The sort time is " + x.getElapsedTime());
	}
	private long startTime;
	private long endTime;
	void start(){
		startTime = System.currentTimeMillis();
	}
	void end(){
		endTime = System.currentTimeMillis();
	}
	long getElapsedTime(){
		return endTime - startTime;
	}
}
