package javaQnA;

import java.util.Arrays;

// Min operations to sort an array
public class MinOperations {

	int minOperations(int[] arr) {
		int minOperations = 0;
		int[] sortedArray = arr.clone();
		Arrays.sort(sortedArray);
		int[] newPermutationArray = arr.clone();
		do {
			for (int i = 0; i + 1 < newPermutationArray.length; i++) {
				int diff = newPermutationArray[i + 1] - newPermutationArray[i];
				int startIndex = i + 1;
				if (diff > 1) {
					int loopStartIndex = startIndex + 1;
					while (loopStartIndex < newPermutationArray.length && newPermutationArray[startIndex]
							- newPermutationArray[loopStartIndex] == loopStartIndex - startIndex) {
						++loopStartIndex;
					}
					--loopStartIndex;
					if (newPermutationArray[startIndex] - newPermutationArray[loopStartIndex] == loopStartIndex
							- startIndex) {
						swap(newPermutationArray, loopStartIndex, startIndex);
						++minOperations;
					}

				} else if (diff < 1) {
					swap(newPermutationArray, i + 1, i);
					++minOperations;
				}
			}
		} while (minOperations < arr.length && !Arrays.equals(newPermutationArray, sortedArray));
		return minOperations;
	}

	public void swap(int[] nums, int n1, int n2) {
		int tmp = nums[n1];
		nums[n1] = nums[n2];
		nums[n2] = tmp;
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

		int[] arr_1 = { 1, 2, 5, 4, 3 };
		int expected_1 = 1;
		int output_1 = minOperations(arr_1);
		check(expected_1, output_1);

		int[] arr_2 = { 3, 1, 2 };
		int expected_2 = 2;
		int output_2 = minOperations(arr_2);
		check(expected_2, output_2);

		// Add your own test cases here

	}

	public static void main(String[] args) {
		new MinOperations().run();
	}
}
