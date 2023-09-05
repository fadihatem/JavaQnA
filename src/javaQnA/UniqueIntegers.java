package javaQnA;

import java.io.IOException;

public class UniqueIntegers {
	public int getUniformIntegerCountInInterval(long A, long B) {
		// Write your code here
		if (A < 1L || B < A || B > 1000000000000L) {
			return 0;
		}
		// Length of start would the same size of the B long number unless first digit
		// is 1 would be length -1
		long uniqueLong = extractUniqueNumber(String.valueOf(B));
		int uniformIntegers = 0;
		while (uniqueLong >= A) {
			++uniformIntegers;
			uniqueLong = extractUniqueNumber(String.valueOf(uniqueLong - 1));
		}
		return uniformIntegers;
	}

	private long extractUniqueNumber(String b) {
		long firstDigit = Long.parseLong(String.valueOf(b.charAt(0)));
		int startLength = b.length();
		long uniqueNumber = Long.parseLong(String.valueOf(firstDigit).repeat(startLength));
		while (uniqueNumber > Long.parseLong(b)) {
			startLength = firstDigit == 1 ? startLength - 1 : startLength;
			firstDigit = firstDigit == 1 ? 9 : firstDigit - 1;
			if (startLength == 0) {
				break;
			}
			uniqueNumber = Long.parseLong(String.valueOf(firstDigit).repeat(startLength));
		}
		return uniqueNumber;
	}

	// These are the tests we use to determine if the solution is correct.
	// You can add your own at the bottom.
	int test_case_number = 1;

	void check(long expected, long output) {
		boolean result = (expected == output);
		char rightTick = '\u2713';
		char wrongTick = '\u2717';
		if (result) {
			System.out.println(rightTick + " Test #" + test_case_number);
		} else {
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected " + expected);
			System.out.print(" Your output: " + output);
			System.out.println();
		}
		test_case_number++;
	}

	private void run() {
		check(5, getUniformIntegerCountInInterval(75, 300));
		check(9, getUniformIntegerCountInInterval(1, 9));
		check(1, getUniformIntegerCountInInterval(999999999999L, 999999999999L));
	}

	public static void main(String[] args) throws IOException {
		new UniqueIntegers().run();
	}
}
