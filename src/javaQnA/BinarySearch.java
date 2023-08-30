package javaQnA;

import java.util.List;

public class BinarySearch {

	Integer binarySearch(List<Integer> arr, int target) {
		int left = 0;
		int right = arr.size() - 1;
		while (left < right) {
			int middle = Math.floorDiv(left + right, 2);
			int middleValue = arr.get(middle);
			if (middleValue == target) {
				return middle;
			} else if (target < middleValue) {// search left
				right = middle - 1;
			} else {// search right
				left = middle + 1;
			}
		}
		return -1;
	}

	// You can add your own at the bottom.
	static int test_case_number = 1;

	static void check(Integer expected, Integer output) {
		boolean result = (expected.equals(output));
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

	public static void main(String[] args) {
		List<Integer> arr = List.of(1, 3, 5, 6, 9);
		check(3, new BinarySearch().binarySearch(arr, 6));
		check(2, new BinarySearch().binarySearch(arr, 5));
		check(-1, new BinarySearch().binarySearch(arr, 7));
	}

}
