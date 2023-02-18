package pkg10.pkg28;

public final class MyStringBuilder2 {

	private String s;

	public MyStringBuilder2() {
		s = "";
	}

	public MyStringBuilder2(char[] chars) {
		s = String.valueOf(chars);
	}

	public MyStringBuilder2(String s) {
		this.s = s;
	}

	public MyStringBuilder2 insert(int offset, MyStringBuilder2 s) {
		String newStr = "";
		int i;
		for (i = 0; i < offset; i++) {
			if (i < offset)
				newStr += this.s.charAt(i) + "";
		}
		newStr += s;
		return new MyStringBuilder2(newStr + substring(offset));
	}

	public MyStringBuilder2 reverse() {
		String newStr = "";
		for (int i = s.length() - 1; i >= 0; i--) {
			newStr += s.charAt(i) + "";
		}
		return new MyStringBuilder2(newStr);
	}

	public MyStringBuilder2 substring(int begin) {
		String newStr = "";
		for (int i = begin; i < s.length(); i++) {
			newStr += s.charAt(i) + "";
		}
		return new MyStringBuilder2(newStr);
	}

	public MyStringBuilder2 toUpperCase() {
		String newStr = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z')
				newStr += (char)(s.charAt(i) - 32) + "";
			else
				newStr += s.charAt(i) + "";
		}
		return new MyStringBuilder2(newStr);
	}

	public String toString() {
		return s;
	}

	public int length() {
		return s.length();
	}

	public char charAt(int index) {
		return s.charAt(index);
	}
        
	public static void main(String[] args) {
		
		char[] chars = {'A', 'n', 't', 'o', 'k'}; 

		MyStringBuilder2 word1 = new MyStringBuilder2("Nakakapagpabagabag");
		MyStringBuilder2 word2 = new MyStringBuilder2(chars);
                
                System.out.print("Start word 1 value: ");
                System.out.println(word1.toString());
                
                System.out.print("Start word 2 value: ");
                System.out.println(word2.toString());
		
		System.out.print("Insert word 2 into word 1 at index 3: ");
		MyStringBuilder2 word3 = word1.insert(3, word2);
		System.out.println(word3);

                System.out.print("Reverse word 1: ");
                System.out.println(word1.reverse().toString());

                System.out.println("Substring of word 1 beginning index 7: " + word1.substring(7));
             
		System.out.println("Word 1 to upper case: " + word1.toUpperCase());
	}
}
