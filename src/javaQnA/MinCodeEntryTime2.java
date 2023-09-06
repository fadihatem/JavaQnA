package javaQnA;

import java.io.IOException;

public class MinCodeEntryTime2 {
	public long getMinCodeEntryTime(int N, int M, int[] C) {
		// Write your code here
		if (N < 3 || N > 50000000 || M < 1 || M > 1000 || C.length < 1 || C.length > N) {
			return 0L;
		}
		int[] codes = new int[2];
		long[] timeToEnterCode = new long[2];
		for (int i = 0; i < M - 1; i++) {
			int timeIndexToUse = codes[0] == 0 ? 0 : 1;
			if (i == 0 && C[i] != 1) {
				timeToEnterCode[timeIndexToUse] += Math.min(C[i] - 1, N - C[i] + 1);
				codes[timeIndexToUse] = C[i];
			}
			if ((C[i + 1] == N && C[i] == 1) || (C[i] == N && C[i + 1] == 1)) {
				timeToEnterCode[timeIndexToUse] += 1;
				codes[timeIndexToUse] = 1;
			} else if (C[i + 1] != C[i]) {
				int min = getRotationaryMin(N, C, i, C[i]);
				int rightIndex = timeIndexToUse;
				for (int j = 0; j < codes.length; j++) {
					int lastValue = codes[j];
					int currMin = lastValue != 0 ? getRotationaryMin(N, C, i, lastValue)
							: Math.min(C[i + 1] - 1, N - C[i + 1] + 1);
					if (currMin < min) {
						min = currMin;
						rightIndex = j;
					}
				}
				if (min > 1) {
					timeToEnterCode[rightIndex] += min;
					codes[rightIndex] = C[i + 1];
				} else {
					timeToEnterCode[timeIndexToUse] += 1;
					codes[timeIndexToUse] = 1;
				}
			}
		}
		return timeToEnterCode[0] + timeToEnterCode[1];
	}

	private int getRotationaryMin(int N, int[] C, int i, int lastValue) {
		int biggestValue = Math.max(C[i + 1], lastValue);
		int smallestValue = Math.min(C[i + 1], lastValue);
		int diff = Math.abs(C[i + 1] - lastValue);
		int rotationaryDistance = smallestValue + N - biggestValue;
		return Math.min(rotationaryDistance, diff);
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
		check(6, getMinCodeEntryTime(10, 4, new int[] { 9, 4, 4, 8 }));
	}

	public static void main(String[] args) throws IOException {
		new MinCodeEntryTime2().run();
	}
}
