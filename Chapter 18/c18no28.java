package pkg18.pkg28;

import java.io.File;
import java.util.Scanner;
import java.util.Stack;

public class c18no28 {
    public static void main(String[] args) {
    
    System.out.print("Enter a directory or a file: ");
    Scanner input = new Scanner(System.in);
    String directory = input.nextLine();

    System.out.println(getSize(new File(directory)) + " bytes");
    input.close();
}
    
    public static long getSize(File file) {
    long size = 0;
		
    Stack<File> stack = new Stack<>();
    if (file.isDirectory())
	stack.push(file);
    else
	size += file.length();
		
    while (!stack.isEmpty()) {
	File currDir = stack.pop();
			
	File[] fileChildren = currDir.listFiles();
	for (File f : fileChildren) {
	    if (f.isDirectory()) 
		stack.push(f);
	    else
		size += f.length();
	}
    }		
        return size;
    }
}
