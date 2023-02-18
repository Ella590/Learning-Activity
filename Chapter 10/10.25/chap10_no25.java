import java.util.ArrayList;
import java.util.Arrays;

public class chap10_no25 {

    public static void main(String[] args) {
       
        String x = "ab#12#453";
        String regex = "#";
        String y = "a?b?gf#e";
        String regex2 = "[?#]";
        String z = "a&n*!g&l!hirap&s*po";
        String regex3 = "[&!*]";

        System.out.println("split(\"ab#12#453\",\"#\"): " + Arrays.toString(split(x, regex)));
        System.out.println("split(\"a?b?gf#e\",\"[?#]\"): " + Arrays.toString(split(y, regex2)));
        System.out.println("split(\"a&c!na&s#\",\"[&!*]\"): " + Arrays.toString(split(z, regex3)));
    }

    public static String[] split(String s, String regex) {
        ArrayList<String> splitList = new ArrayList<>();
        if (regex == null || regex.length() < 1) {
            return new String[]{"regex must not be null and length must be greater than 0"};
        }

        ArrayList<Character> splitters = new ArrayList<>();
        for (char ch : regex.toCharArray()) {
            if (ch == '[' || ch == ']') continue;
            splitters.add(ch);
        }
        String subString = "";
        for (int q = 0; q < s.length(); q++) {
            if (splitters.contains(s.charAt(q))) {
                if (subString.length() > 0) {
                    splitList.add(subString);
                    subString = "";
                }

                splitList.add(s.charAt(q) + "");
                q++;
                while (q < s.length()) {
                    if (!splitters.contains(s.charAt(q))) {
                        subString = subString.concat(s.charAt(q) + "");
                    } else {
                        splitList.add(subString);
                        subString = "";
                        splitList.add(s.charAt(q) + "");
                        break;
                    }
                    q++;
                }
            } else {
                subString = subString.concat(s.charAt(q) + "");
            }
        }
        if (subString.length() > 0) {
            splitList.add(subString);
        }
        return splitList.toArray(new String[]{});
    }
}
