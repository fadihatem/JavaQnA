package javaQnA;

import java.io.IOException;

public class MinCodeEntryTime {
	public long getMinCodeEntryTime(int N, int M, int[] C) {
		// Write your code here
		if (N < 3 || N > 50000000 || M < 1 || M > 1000 || C.length < 1 || C.length > N) {
			return 0L;
		}
		long timeToEnterCode = 0L;
		for (int i = 0; i < M - 1; i++) {
			if (i == 0 && C[i] != 1) {
				timeToEnterCode += Math.min(C[i] - 1, N - C[i] + 1);
			}
			if ((C[i + 1] == N && C[i] == 1) || (C[i] == N && C[i + 1] == 1)) {
				timeToEnterCode += 1;
			} else if (C[i + 1] != C[i]) {
				int biggestValue = Math.max(C[i + 1], C[i]);
				int smallestValue = Math.min(C[i + 1], C[i]);
				int diff = Math.abs(C[i + 1] - C[i]);
				int rotationaryDistance = smallestValue + N - biggestValue;
				timeToEnterCode += Math.min(rotationaryDistance, diff);
			}
		}
		return timeToEnterCode;
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
		check(2, getMinCodeEntryTime(3, 3, new int[] { 1, 2, 3 }));
		check(11, getMinCodeEntryTime(10, 4, new int[] { 9, 4, 4, 8 }));
	}

	public static void main(String[] args) throws IOException {
		new MinCodeEntryTime().run();
	}
}
