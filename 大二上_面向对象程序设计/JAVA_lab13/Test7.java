import java.util.*;
import java.io.*;

class Test7 {
    public static void main(String[] args) throws Exception {
        File file = new File("Exercise12_15.txt");
        if (file.exists()) {
            System.out.println("File already exists");
            System.exit(0);
        }

        try (
                // Create output file
                PrintWriter output = new PrintWriter(file);
        ) {
            // Write 100 integers created radomly to file
            for (int i = 0; i < 100; i++) {
                output.print(((int)(Math.random() * 500) + 1));
                output.print(" ");
            }
        }

        // Crate and ArrayList
        ArrayList<Integer> list = new ArrayList<>();

        try(
                // Create input file
                Scanner input = new Scanner(file);
        ) {
            // Read the data back from the file
            while (input.hasNext()) {
                list.add(input.nextInt());
            }
        }

        // Sort array list
        Collections.sort(list);

        // Display data in increasing order
        for (int i = 0; i < list.size(); i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(list.get(i)+" ");
        }
    }
}

