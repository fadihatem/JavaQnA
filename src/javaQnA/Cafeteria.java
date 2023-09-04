package javaQnA;

import java.io.IOException;
import java.util.Arrays;

public class Cafeteria {
	public static long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
		// Write your code here
		long minValue = Arrays.stream(S).min().getAsLong();
		long maxValue = Arrays.stream(S).max().getAsLong();
		if (N > 1000000000000000L || N < 1L || K < 1L || K > N || M < 1L || M > 500000L || M > N || minValue < 1L
				|| maxValue > N)
			return 0L;
		boolean[] seatsTaken = new boolean[(int) N];
		for (int i = 0; i < M; i++) {
			int start = findStart(K, S[i]);
			int end = findEnd(N, K, S[i]);
			for (int j = start; j < end; j++) {
				seatsTaken[j] = true;
			}
		}
		long count = 0L;
		for (int i = 0; i < N; i++) {
			if (!seatsTaken[i]) {
				int end = (int) (i + K + 1 < N ? i + K + 1 : N);
				boolean seatRightAvailable = end < N ? true : false;
				// right end check
				for (int j = i + 1; j < end; j++) {
					if (seatsTaken[j]) {
						seatRightAvailable = false;
						break;
					}
				}
				if (seatRightAvailable) {
					++count;
				}
				// left end check
				int start = (int) (i - K > 0 ? i - K : 0);
				boolean seatLeftAvailable = start > 0 ? true : false;
				for (int j = start; j < i; j++) {
					if (seatsTaken[j]) {
						seatLeftAvailable = false;
						break;
					}
				}
				if (seatLeftAvailable) {
					++count;
				}
			}
		}
		return count;
	}

	private static int findEnd(long N, long K, long value) {
		int end;
		if (value + K >= N) {
			end = (int) N;
		} else {
			end = (int) (value + K);
		}
		return end;
	}

	private static int findStart(long K, long value) {
		int start;
		if (value <= K) {
			start = 0;
		} else {
			start = (int) (value - K - 1);
		}
		return start;
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
		check(3, getMaxAdditionalDinersCount(10, 1, 2, new long[] { 2, 6 }));
		check(1, getMaxAdditionalDinersCount(15, 2, 3, new long[] { 11, 6, 14 }));
	}

	public static void main(String[] args) throws IOException {
		new Cafeteria().run();
	}
}
