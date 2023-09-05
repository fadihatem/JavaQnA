package javaQnA;

// Rotation cipher typical problem where strings are shifted by a certain value provided as input
public class RotationFactor {
	private static final int ZERO_CODEPOINT = "0".codePointAt(0);
	private static final int NINE_CODEPOINT = "9".codePointAt(0);
	private static final int UPPER_CASE_A_CODEPOINT = "A".codePointAt(0);
	private static final int UPPER_CASE_Z_CODEPOINT = "Z".codePointAt(0);
	private static final int LOWER_CASE_Z_CODEPOINT = "z".codePointAt(0);
	private static final int LOWER_CASE_A_CODEPOINT = "a".codePointAt(0);

	String rotationalCipher(String input, int rotationFactor) {
		int alphaLimit = 26;// 26 letters
		int numbersLimit = 10;// 10 digits
		int rotationFactorAlpha = rotationFactor % alphaLimit;
		int rotationFactorNumbers = rotationFactor % numbersLimit;
		int size = input.length();
		if (!(size >= 1 && size <= 1000000 && rotationFactor >= 1 && rotationFactor <= 1000000)) {
			System.out.println("input length should be 1 and 1 million likewise for rotation factor");
			return input;
		}
		StringBuilder sb = new StringBuilder();
		for (int s : input.codePoints().toArray()) {
			if (Character.isAlphabetic(s)) {
				boolean lowerCase = s >= LOWER_CASE_A_CODEPOINT && s <= LOWER_CASE_Z_CODEPOINT;
				int alphaCodePoint = s == LOWER_CASE_Z_CODEPOINT ? LOWER_CASE_A_CODEPOINT + rotationFactorAlpha - 1
						: s == UPPER_CASE_Z_CODEPOINT ? UPPER_CASE_A_CODEPOINT + rotationFactorAlpha - 1
								: s + rotationFactorAlpha;
				if (lowerCase && alphaCodePoint > LOWER_CASE_Z_CODEPOINT) {
					alphaCodePoint = LOWER_CASE_A_CODEPOINT + alphaCodePoint - LOWER_CASE_Z_CODEPOINT - 1;
				} else if (!lowerCase && alphaCodePoint > UPPER_CASE_Z_CODEPOINT) {
					alphaCodePoint = UPPER_CASE_A_CODEPOINT + alphaCodePoint - UPPER_CASE_Z_CODEPOINT - 1;
				}
				sb.append(Character.toChars(alphaCodePoint));
			} else if (Character.isDigit(s)) {
				int numericCodePoint = s + rotationFactorNumbers;
				if (numericCodePoint > NINE_CODEPOINT) {
					numericCodePoint = ZERO_CODEPOINT + numericCodePoint - NINE_CODEPOINT - 1;
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
		String input_3 = "abcdZXYzxy-999.@";
		int rotationFactor_3 = 200;
		String expected_3 = "stuvRPQrpq-999.@";
		String output_3 = rotationalCipher(input_3, rotationFactor_3);
		check(expected_3, output_3);
	}

	public static void main(String[] args) {
		new RotationFactor().run();
	}
}
