package javaQnA;

import java.io.IOException;
import java.util.Arrays;

public class PairSums {
	int numberOfWays(int[] arr, int k) {
		// Write your code here
		int minValue = Arrays.stream(arr).min().getAsInt();
		int maxValue = Arrays.stream(arr).max().getAsInt();
		if (minValue < 1 || maxValue > 1000000000 || k > 1000000000 || arr.length > 100000) {
			return 0;
		}
		int ways = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= k) {
				continue;
			}
			int value = arr[i];
			int startIndex = i + 1;
			while (startIndex < arr.length) {
				if (arr[startIndex] == k - value) {
					++ways;
				}
				startIndex++;
			}
		}
		return ways;
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
		check(2, numberOfWays(new int[] { 1, 2, 3, 4, 3 }, 6));
		check(4, numberOfWays(new int[] { 1, 5, 3, 3, 3 }, 6));
	}

	public static void main(String[] args) throws IOException {
		new PairSums().run();
	}
}
