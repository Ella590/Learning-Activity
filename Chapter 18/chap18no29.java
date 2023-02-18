package pkg18.pkg29;

import java.io.File;
import java.util.Scanner;

public class chap18no29 {

    public static void main(String[] args) {
        System.out.print("Enter directory: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine().trim();
        File file = new File(directory);
        
        if (file.isDirectory()) {
            System.out.println("The directory " + file.getName() + " has " + getFileCount(file) + " files.");
        } else {
            System.out.println("Error! Input is not a directory.");
        }
    }

    public static long getFileCount(File file) {
        long TOTAL = 0;
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isFile()) {
                    TOTAL += 1;
                }

            }
        }
        return TOTAL;
    }
}