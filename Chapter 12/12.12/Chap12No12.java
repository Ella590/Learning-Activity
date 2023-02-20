package pkg12.pkg12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Chap12No12 {

    public static void Formatmain(String[] args) {
        ArrayList<String> lines = new ArrayList<>();
        if(args.length < 1) {
            System.out.println("Usage: java Exercise 12_12 sourceFile");
            return;
        }

        File file = new File(args[0]);

        if(file.exists()) {
            try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    lines.add(line);
                }

                for (int i = 1; i < lines.size(); i++) {
                    if (lines.get(i).contains("{")) {
                        String orgLine = lines.get(i);
                        orgLine = orgLine.replace('{', ' ');
                        String targetLine = lines.get(i - 1);
                        targetLine = targetLine + " {";
                        lines.set(i, orgLine);
                        lines.set(i - 1, targetLine);
                    }
                }

                PrintWriter writer = new PrintWriter(file);

                for (String line : lines) {
                    writer.println(line);
                }
                writer.close();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found to exist: " + e);
            }
        }
        System.out.println("Converting Java source code done!");
    }
    public static void main(String[] args) throws Exception {
        String[] format = {"C:\\Users\\Ella\\Desktop\\PROG\\12.12\\src\\pkg12\\pkg12\\Testing.java"};
        Chap12No12.Formatmain(format);
    }
}