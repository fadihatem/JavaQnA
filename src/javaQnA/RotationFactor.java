package javaQnA;

public class RotationFactor {
	private static final String ZERO = "0";
	private static final String NINE = "9";
	private static final String UPPER_CASE_A = "A";
	private static final String UPPER_CASE_Z = "Z";
	private static final String LOWER_CASE_Z = "z";
	private static final String LOWER_CASE_A = "a";

	// Add any helper functions you may need here

	String rotationalCipher(String input, int rotationFactor) {
		int alphaLimit = 26;
		int numbersLimit = 10;
		int rotationFactorAlpha = rotationFactor % alphaLimit;
		int rotationFactorNumbers = rotationFactor % numbersLimit;
		int size = input.length();
		if (!(size >= 1 && size <= 1000000 && rotationFactor >= 1 && rotationFactor <= 1000000)) {
			System.out.println("input length should be 1 and 1 million likewise for rotation factor");
			return input;
		}
		StringBuilder sb = new StringBuilder();
		for (int s : input.codePoints().toArray()) {
			if (s == "N".codePointAt(0)) {
				System.out.println("hello");
			}
			if (Character.isAlphabetic(s)) {
				boolean lowerCase = s >= LOWER_CASE_A.codePointAt(0) && s <= LOWER_CASE_Z.codePointAt(0);
				int alphaCodePoint = s == LOWER_CASE_Z.codePointAt(0)
						? LOWER_CASE_A.codePointAt(0) + rotationFactorAlpha - 1
						: s == UPPER_CASE_Z.codePointAt(0) ? UPPER_CASE_A.codePointAt(0) + rotationFactorAlpha - 1
								: s + rotationFactorAlpha;
				if (lowerCase && alphaCodePoint > LOWER_CASE_Z.codePointAt(0)) {
					alphaCodePoint = LOWER_CASE_A.codePointAt(0) + alphaCodePoint - LOWER_CASE_Z.codePointAt(0) - 1;
				} else if (!lowerCase && alphaCodePoint > UPPER_CASE_Z.codePointAt(0)) {
					alphaCodePoint = UPPER_CASE_A.codePointAt(0) + alphaCodePoint - UPPER_CASE_Z.codePointAt(0) - 1;
				}
				sb.append(Character.toChars(alphaCodePoint));
			} else if (Character.isDigit(s)) {
				int numericCodePoint = s == NINE.codePointAt(0) ? ZERO.codePointAt(0) + rotationFactorNumbers - 1
						: s + rotationFactorNumbers;
				if (numericCodePoint > NINE.codePointAt(0)) {
					numericCodePoint = ZERO.codePointAt(0) + numericCodePoint - NINE.codePointAt(0) - 1;
				}
				sb.append(Character.toChars(numericCodePoint));
			} else {
				sb.append(Character.toChars(s));
			}
		}
		return sb.toString();
	}

	// These are the tests we use to determine if the solution is correct.
	// You can add your own at the bottom.
	int test_case_number = 1;

	void check(String expected, String output) {
		boolean result = (expected.equals(output));
		char rightTick = '\u2713';
		char wrongTick = '\u2717';
		if (result) {
			System.out.println(rightTick + " Test #" + test_case_number);
		} else {
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
			printString(expected);
			System.out.print(" Your output: ");
			printString(output);
			System.out.println();
		}
		test_case_number++;
	}

	void printString(String str) {
		System.out.print("[\"" + str + "\"]");
	}

	public void run() {
		String input_1 = "Zebra-493?";
		int rotationFactor_1 = 3;
		String expected_1 = "Cheud-726?";
		String output_1 = rotationalCipher(input_1, rotationFactor_1);
		check(expected_1, output_1);

		String input_2 = "abcdefghijklmNOPQRSTUVWXYZ0123456789";
		int rotationFactor_2 = 39;
		String expected_2 = "nopqrstuvwxyzABCDEFGHIJKLM9012345678";
		String output_2 = rotationalCipher(input_2, rotationFactor_2);
		check(expected_2, output_2);

		// Add your own test cases here

	}

	public static void main(String[] args) {
		new RotationFactor().run();
	}
}
