package com.dsa.practise;

public class MaximumSubArraySum {

	public static void main(String[] args) {
		// int array[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		int array[] = { 25, -27, 28 };

		System.out.println(findMaxSubArraySum(array));
	}

	private static int findMaxSubArraySum(int[] array) {

		int sum = 0, maxSum = 0;
		for (int i = 0; i < array.length; i++) {
			if ((array[i] + sum) > 0) {
				sum = sum + array[i];
				maxSum = Math.max(maxSum, sum);
			} else {
				sum = 0;
			}
		}

		return maxSum;
	}

}
