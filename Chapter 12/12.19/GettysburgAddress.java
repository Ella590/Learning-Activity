package pkg12.pkg19;

import java.net.URL;
import java.util.Scanner;


public class GettysburgAddress {
    public static void main(String[] args) {
    try {


        URL url = new URL(
        "http://cs.armstrong.edu/liang/data/Lincoln.txt");


        int counter = 0;
        Scanner input = new Scanner(url.openStream());
        while (input.hasNext()) {
           
            if (Character.isLetter((input.next()).charAt(0))) {
                counter++;
            }
        }


        System.out.println(
            "Number of words in text document is: " +
            counter);
    }
    catch (Exception e) {
        System.out.println("Error");
    }
}
}
