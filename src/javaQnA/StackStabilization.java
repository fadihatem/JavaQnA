package javaQnA;

import java.util.Arrays;

public class StackStabilization {
	public int getMinimumDeflatedDiscCount(int N, int[] R) {
		int minValue = Arrays.stream(R).min().getAsInt();
		int maxValue = Arrays.stream(R).max().getAsInt();
		if (N < 1 || N > 50 || minValue < 1 || maxValue > 1000000) {
			return 0;
		}
		// If last element has value less than length of array then it is impossible to
		// make stable
		if (R[R.length - 1] < R.length) {
			return -1;
		}
		int minDeflations = 0;
		for (int i = R.length - 1; i > 0; i--) {
			if (R[i - 1] >= R[i]) {
				++minDeflations;
				R[i - 1] = R[i] - 1;
			}
		}
		return minDeflations;
	}

	// These are the tests we use to determine if the solution is correct.
	// You can add your own at the bottom.
	int test_case_number = 1;

	void check(int expected, int output) {
		boolean result = (expected == output);
		char rightTick = '\u2713';
		char wrongTick = '\u2717';
		if (result) {
			System.out.println(rightTick + " Test #" + test_case_number);
		} else {
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected ");
			printInteger(expected);
			System.out.print(" Your output: ");
			printInteger(output);
			System.out.println();
		}
		test_case_number++;
	}

	void printInteger(int n) {
		System.out.print("[" + n + "]");
	}

	public void run() {

		int[] arr_1 = { 2, 5, 3, 6, 5 };
		int expected_1 = 3;
		int output_1 = getMinimumDeflatedDiscCount(5, arr_1);
		check(expected_1, output_1);

		int[] arr_2 = { 100, 100, 100 };
		int expected_2 = 2;
		int output_2 = getMinimumDeflatedDiscCount(3, arr_2);
		check(expected_2, output_2);

		int[] arr_3 = { 6, 5, 4, 3 };
		int expected_3 = -1;
		int output_3 = getMinimumDeflatedDiscCount(4, arr_3);
		check(expected_3, output_3);
		// Add your own test cases here

	}

	public static void main(String[] args) {
		new StackStabilization().run();
	}
}
