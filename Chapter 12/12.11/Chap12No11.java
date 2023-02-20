package pkg12.pkg11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Chap12No11 {
    public static void StrRemove(String[] args) throws Exception {

        if (args.length != 2) {
            System.out.println("Invalid arguments.");
            System.out.println("Usage: java Exercise12_11 John filename");
            System.exit(1);
        }

        File filename = new File(args[1]);
        if (!filename.exists()) {
            System.out.println(args[1] + " does not exist.");
            System.out.println(2);
        }

        String s = "";
        try {
            Scanner input = new Scanner(filename);
            while (input.hasNext()) {
                s += input.nextLine();
            }
        } 
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        s = s.replaceAll(args[0], "");
       
        try {
            PrintWriter output = new PrintWriter(filename);
            output.write(s);
            output.close();
        } 
        
        catch (FileNotFoundException e) {
            e.printStackTrace();
      }
        
        System.out.println("Removing String Complete");
        
    }
    
    public static void main(String[] args) throws Exception {
        String[] removestr = {"John", "C:\\Users\\Ella\\Desktop\\PROG\\12.11\\src\\pkg12\\pkg11\\txt file for 12.11.txt"};
        Chap12No11.StrRemove(removestr);
    }
    
}
