package javaQnA;

import java.io.IOException;
import java.util.Stack;

public class Kaitenzushi {
	public int getMaximumEatenDishCount(int N, int[] D, int K) {
		// Write your code here
		if (N < 1 || N > 500000 || K < 1 || K > N || D.length < 1 || D.length > 1000000) {
			return 0;
		}
		Stack<Integer> eatenDishes = new Stack<>();
		for (int i = 0; i < N; i++) {
			int positionOnStack = eatenDishes.search(D[i]);
			if (positionOnStack == -1 || positionOnStack > K) {
				eatenDishes.add(D[i]);
			}
		}
		return eatenDishes.size();
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
		check(5, getMaximumEatenDishCount(6, new int[] { 1, 2, 3, 3, 2, 1 }, 1));
		check(4, getMaximumEatenDishCount(6, new int[] { 1, 2, 3, 3, 2, 1 }, 2));
		check(2, getMaximumEatenDishCount(7, new int[] { 1, 2, 1, 2, 1, 2, 1 }, 2));
	}

	public static void main(String[] args) throws IOException {
		new Kaitenzushi().run();
	}
}
