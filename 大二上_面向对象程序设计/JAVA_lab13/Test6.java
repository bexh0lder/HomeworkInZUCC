import java.io.File;
import java.io.FileInputStream;
public class Test6 {
    public static void main(String[] args) throws Exception{
        compute(readFile("C:\\Users\\12196\\Desktop\\score.txt"));
    }
    public static int[] readFile(String path) throws Exception{
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        int size = (int)file.length();
        byte[] b = new byte[size];
        fis.read(b);
        String document = new String(b);
        String[] strs = document.split(" ");
        int length = strs.length;
        int [] scores = new int[length];
        for(int i=0;i<length;i++){
            scores[i] = Integer.parseInt(strs[i]);
        }
        return scores;
    }
    public static void compute(int[] score){
        int length = score.length;
        int sum = 0;
        System.out.print("Total：");
        for(int x : score){
            sum += x;
        }
        System.out.println(sum);
        System.out.print("Average：");
        System.out.println(sum/length);
    }
}