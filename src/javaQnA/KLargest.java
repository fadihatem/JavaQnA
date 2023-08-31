package javaQnA;

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class KLargest {
	public int findKthLargest(int[] nums, int k) {
		Arrays.sort(nums);
		return nums[k > nums.length ? nums.length - 1 : nums.length - k];
	}

	public int findKthLargestPQ(int[] nums, int k) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
		for (int i : nums) {
			q.offer(i);
			if (q.size() > k) {
				q.poll();
			}
		}
		return q.peek();
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

	public void run() throws IOException {
		int[] integers = new int[] { 1, 10, 6, 4, 5 };
		System.out.println("Testing using int array sort");
		check(4, findKthLargest(integers, 4));
		check(10, findKthLargest(integers, 1));
		System.out.println("Testing using priority queues");
		check(4, findKthLargestPQ(integers, 4));
		check(10, findKthLargestPQ(integers, 1));
	}

	public static void main(String[] args) throws IOException {
		new KLargest().run();
	}
}
