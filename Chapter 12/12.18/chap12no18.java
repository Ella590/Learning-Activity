package pkg12.pkg18;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class chap12no18 {

    public static void src(String[] args) {
	if (args.length != 1) {
		System.out.println("Usage: java Exercise12_18 srcRootDirectory");
		System.exit(1);
	}
    }
    
        public static void main(String[] args) throws FileNotFoundException {
	File sourceDir = new File("C:\\Users\\Ella\\Desktop\\Chapter 12\\12.18\\srcRootDirectory"); 
	if (!sourceDir.exists()) {
		System.out.println("Source directory " + sourceDir + " does not exist");
		System.exit(2);
	}

	File[] srcList = sourceDir.listFiles();
	
	File temp = new File("C:\\Users\\Ella\\Desktop\\Chapter 12\\12.18\\srcRootDirectory\\chapteri\\testFile1.java");
	
	for (int i = 0; i < srcList.length; i++)
		
	  if (srcList[i].isDirectory())
	    if (srcList[i].getName().contains("chapter")) {

	    	File[] subList = srcList[i].listFiles();
	    	
	    	for (int j = 0; j < subList.length; j++)
	    		if (subList[j].isFile())
	    	    if (subList[j].getName().contains(".java"))

	    	    	try (PrintWriter tempout = new PrintWriter(temp);
	    	    		Scanner tempin = new Scanner(temp);
	    	    		PrintWriter output = new PrintWriter(subList[j]);
	    	    		Scanner input = new Scanner(subList[j]);){
	    	    		
	    	    		while (input.hasNextLine()) {
	    	    			tempout.println(input.nextLine());
	    	    		}
	    	    		
	    	    		output.println("package " + srcList[i].getName()+";");
	    	    		
	    	    		while (tempin.hasNextLine())
	    	    			output.println(input.nextLine());
	   	    	}
	    }
          System.out.println("Done!");
        }
}
