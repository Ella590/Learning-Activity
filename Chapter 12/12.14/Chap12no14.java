package pkg12.pkg14;

import java.io.File;

public class Chap12no14 {
public static void main (String [] args) throws Exception{
java.util.Scanner sc = new java.util.Scanner (System.in);
System.out.println("Enter the File: ");
File file = new File(sc.nextLine());
if (!file.exists()) {
System.out.println ("Your file " + file + "does not exist.");
System.exit(1);
}
int number = 0;
double total = 0;
try (
java.util.Scanner inputfile = new java.util.Scanner (file);
)
{
while (inputfile.hasNext()) {
total += inputfile.nextInt();
number++;
}
}
System.out.println("Total Scores: " + total );
System.out.println("Average Scores: " + (total/number));
}
}

