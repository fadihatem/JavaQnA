package javaQnA;

import java.util.List;

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

	class OneEditAwayTestCase {
		String input_1;
		String input_2;
		boolean expected;

		OneEditAwayTestCase(String input_1, String input_2, boolean expected) {
			this.input_1 = input_1;
			this.input_2 = input_2;
			this.expected = expected;
		}
	}

	public void run() {
		List<OneEditAwayTestCase> tests = List.of(new OneEditAwayTestCase("Cheud-426?", "Cheud-726?", true),
				new OneEditAwayTestCase("abcdefghijklmNOPQRSTUVWXYZ012", "nopqrstuvwxyzABCDEFGHIJKLM9012345678", false),
				new OneEditAwayTestCase("abcdefghijklmNOPQRSTUVWXYZ0123456789", "nopqrstuvwxyzABCDEFGHIJKLM90", false),
				new OneEditAwayTestCase("abcdefghijklmNOPQRSTUVWXYZ01345678", "abcdefghijklmNOPQRSTUVWXYZ012345678",
						true),
				new OneEditAwayTestCase("abcdefghijklmNOPQRSTUVWXYZ012345678", "abcdefghijklmNOPQRSTUVWXYZ01345678",
						true),
				new OneEditAwayTestCase("abc", "abd", true));

		for (OneEditAwayTestCase test : tests) {
			check(test.expected, one_edit_apart(test.input_1, test.input_2));
		}
		// Add your own test cases here

	}

	public static void main(String[] args) {
		new OneEditAway().run();
	}
}
