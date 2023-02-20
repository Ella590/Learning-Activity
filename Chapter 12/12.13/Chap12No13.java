package pkg12.pkg13;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Chap12No13 {
    public static void main(String[] args) throws IOException {
        
        File sfile = new File("C:\\Users\\Ella\\Desktop\\PROG\\12.13\\src\\pkg12\\pkg13\\text sample.txt");
        if (!sfile.exists()) {
            System.out.println("Please check your file name and try again");
            System.exit(1);
        }

        int chars = 0; 
        int words = 0; 
        int lines = 0;
        String tem = "";

        try (Scanner input = new Scanner(sfile);) {

            while (input.hasNext()) {
                tem = input.nextLine();
                lines++;
                String[] tempArr = tem.split(" ");
                words += tempArr.length;

                for (int i = tempArr.length - 1; i >= 0; i--) {
                    chars += tempArr[i].length();
                }
            }
        }

        System.out.println("File " + sfile.getName() + " has:");
        System.out.println(chars + " characters");
        System.out.println(words + " words");
        System.out.println(lines + " lines");

    }
}