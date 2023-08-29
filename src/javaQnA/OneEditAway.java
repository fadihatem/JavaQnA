package javaQnA;

public class OneEditAway {
	boolean one_edit_apart(String s1, String s2) {
		if (s1.length() > s2.length()) {
			int secondLength = s2.length();
			int origDiff = s1.length() - secondLength;
			// Concatenate both the string str1 and str2 and store it in str1
			s1 = s1 + s2;
			int newDiff = s1.length() - secondLength;
			// Extract str2 from updated str1
			s2 = s1.substring(0, newDiff);
			// Extract str1 from updated str1
			s1 = s1.substring(secondLength + origDiff);
		}
		if (s2.length() - s1.length() > 1)
			return false;
		boolean saw_difference = false;
		for (int i = 0, j = 0; i < s1.length(); ++i, ++j) {
			if (s1.charAt(i) != s2.charAt(j)) {
				if (saw_difference)
					return false;
				saw_difference = true;
				if (s2.length() > s1.length()) {
					--i;
				}
			}
		}
		return saw_difference || s2.length() != s1.length();
	}

	// These are the tests we use to determine if the solution is correct.
	// You can add your own at the bottom.
	int test_case_number = 1;

	void check(boolean expected, boolean output) {
		boolean result = expected == output;
		char rightTick = '\u2713';
		char wrongTick = '\u2717';
		if (result) {
			System.out.println(rightTick + " Test #" + test_case_number);
		} else {
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected " + expected);
			System.out.println();
		}
		test_case_number++;
	}

	void printString(String str) {
		System.out.print("[\"" + str + "\"]");
	}

	public void run() {
		String input_1 = "Cheud-426?";
		String input_2 = "Cheud-726?";
		boolean expected_1 = true;
		boolean output_1 = one_edit_apart(input_1, input_2);
		check(expected_1, output_1);

		String input_3 = "abcdefghijklmNOPQRSTUVWXYZ012";
		String input_4 = "nopqrstuvwxyzABCDEFGHIJKLM9012345678";
		boolean expected_2 = false;
		boolean output_2 = one_edit_apart(input_3, input_4);
		check(expected_2, output_2);

		String input_5 = "abcdefghijklmNOPQRSTUVWXYZ0123456789";
		String input_6 = "nopqrstuvwxyzABCDEFGHIJKLM90";
		boolean expected_3 = false;
		boolean output_3 = one_edit_apart(input_5, input_6);
		check(expected_3, output_3);

		String input_7 = "abcdefghijklmNOPQRSTUVWXYZ01345678";
		String input_8 = "abcdefghijklmNOPQRSTUVWXYZ012345678";
		boolean expected_4 = true;
		boolean output_4 = one_edit_apart(input_7, input_8);
		check(expected_4, output_4);

		String input__9 = "abcdefghijklmNOPQRSTUVWXYZ012345678";
		String input_10 = "abcdefghijklmNOPQRSTUVWXYZ01345678";
		boolean expected_5 = true;
		boolean output_5 = one_edit_apart(input__9, input_10);
		check(expected_5, output_5);

		String input_11 = "abc";
		String input_12 = "abd";
		boolean expected_6 = true;
		boolean output_6 = one_edit_apart(input_11, input_12);
		check(expected_6, output_6);

		// Add your own test cases here

	}

	public static void main(String[] args) {
		new OneEditAway().run();
	}
}
