package javaQnA;

import java.io.IOException;
import java.util.Arrays;

public class Hops {
	public long getSecondsRequired(long N, int F, long[] P) {
		// Write your code here
		long minValue = Arrays.stream(P).min().getAsLong();
		long maxValue = Arrays.stream(P).max().getAsLong();
		if (N < 2 || N > 1000000000000L || F < 1 || F > 500000L || minValue < 1 || maxValue > N - 1) {
			return 0;
		}
		Arrays.sort(P);
		int jumps = 0;
		for (int i = 0; i <= P.length - 1; i++) {
			if (i != P.length - 1) {
				int start = i;
				int end = i + 1;
				while (P[end] - P[start] > 1 && start <= P.length - 2) {
					jumps += P[i + 1] - P[i];
					start++;
					end++;
				}
			} else {
				jumps += 2;
			}
		}
		return jumps;
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
		check(2, getSecondsRequired(3, 1, new long[] { 1 }));
		check(4, getSecondsRequired(6, 3, new long[] { 5, 2, 4 }));
	}

	public static void main(String[] args) throws IOException {
		new Hops().run();
	}
}
