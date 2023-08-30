package javaQnA;

import java.util.Arrays;

public class BubbleSort {

	static void bubbleSort(int[] arr) {
		boolean swapped = false;
		int startIndex = 0;
		while (!swapped) {
			swapped = false;
			while (startIndex < arr.length - 1) {
				if (arr[startIndex] > arr[startIndex + 1]) {
					swap(arr, startIndex, startIndex + 1);
					swapped = true;
				}
				startIndex++;
			}
		}

	}

	static void swap(int[] arr, int firstIndex, int secondIndex) {
		int temp = arr[secondIndex];
		arr[secondIndex] = arr[firstIndex];
		arr[firstIndex] = temp;
	}

	// You can add your own at the bottom.
	static int test_case_number = 1;

	static void check(int[] expected, int[] output) {
		boolean result = Arrays.equals(expected, output);
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
		int[] arr = new int[] { 3, 1, 5, 6, 8, 7 };
		int[] arrSorted = new int[] { 3, 1, 5, 6, 8, 7 };
		Arrays.sort(arrSorted);
		bubbleSort(arr);
		check(arrSorted, arr);
	}
}
