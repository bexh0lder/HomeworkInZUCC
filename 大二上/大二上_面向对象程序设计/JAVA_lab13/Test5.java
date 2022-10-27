import java.util.*;
import java.io.*;
public class Test5 {
	public static void main(String[] argus) throws Exception {
		File file = new File("temp2.txt");
		if(!file.exists()) {
			System.out.println("File Loan.java does not exists");
			System.exit(2);
		}
		
		int characters = 0;
		int words = 0;
		int lines = 0;
		try (
				// Create input file
				Scanner input = new Scanner(file);
			) {
				while (input.hasNext()) {
					lines++;
					String line = input.nextLine();
					characters += line.length();
				}
			}

			try (
				// Create input file
				Scanner input = new Scanner(file);
			) {
				while (input.hasNext()) {
					String line = input.next();
					words++;
				}
			}
	}
}
