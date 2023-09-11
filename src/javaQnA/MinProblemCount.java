package javaQnA;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MinProblemCount {
	public int getMinProblemCount(int N, int[] S) {
		int minValue = Arrays.stream(S).min().getAsInt();
		int maxValue = Arrays.stream(S).max().getAsInt();
		if (N < 1 || N > 500000 || minValue < 1 || maxValue > 1000000 || maxValue < 1 || S.length != N) {
			return 0;
		}
		Map<Boolean, List<Integer>> partitions = Arrays.stream(S).boxed()
				.collect(Collectors.partitioningBy(x -> x % 2 == 0));

		int maxEven = partitions.get(true).size() > 0 ? partitions.get(true).stream().mapToInt(v -> v).max().getAsInt()
				: 0;
		int maxOdd = partitions.get(false).size() > 0 ? partitions.get(false).stream().mapToInt(v -> v).max().getAsInt()
				: 0;
		return maxValue == maxEven ? maxEven / 2 + maxOdd % 2 : maxOdd / 2 + maxOdd % 2;
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
			System.out.print(wrongTick + " Test #" + test_case_number + ": Expected " + expected);
			System.out.print(" Your output: " + output);
			System.out.println();
		}
		test_case_number++;
	}

	private void run() {
		check(4, getMinProblemCount(6, new int[] { 1, 2, 3, 4, 5, 6 }));
		check(3, getMinProblemCount(4, new int[] { 4, 3, 3, 4 }));
		check(4, getMinProblemCount(4, new int[] { 2, 4, 6, 8 }));
		check(3, getMinProblemCount(3, new int[] { 3, 1, 4 }));
	}

	public static void main(String[] args) throws IOException {
		new MinProblemCount().run();
	}
}
