package com.coding.problems;

public class JumpGame {

	public static void main(String[] args) {
		int[] array = { 1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9 };

		System.out.println(jumpReachable(array));

		// System.out.println(jumpMinCount(array));
		System.out.println(minJumps(array, 8));
	}

	public static int jumpReachable(int[] nums) {
		int reachable = 0, jumps = 0;

		for (int i = 0; i < nums.length - 1; i++) {

			if (reachable < i)
				return -1;

			reachable = Math.max(reachable, i + nums[i]);

		}

		// System.out.println(jumps);
		return 1;
	}

//	public static int jumpMinCount(int[] nums) {
//		
//		int jumps = 0, curEnd = 0, curFarthest = 0;
//		
//		for (int i = 0; i < nums.length - 1; i++) {
//			curFarthest = Math.max(curFarthest, i + nums[i]);
//			
//			if (i == curEnd) {
//				jumps++;
//				curEnd = curFarthest;
//			}
//		}
//		return jumps;
//	}

	private static int minJumps(int[] arr, int n) {
		// jumps[n-1] will hold the
		int jumps[] = new int[n];
		// result
		int i, j;

		// if first element is 0,
		if (n == 0 || arr[0] == 0)
			return Integer.MAX_VALUE;
		// end cannot be reached

		jumps[0] = 0;

		// Find the minimum number of jumps to reach arr[i]
		// from arr[0], and assign this value to jumps[i]
		for (i = 1; i < n; i++) {
			jumps[i] = Integer.MAX_VALUE;
			for (j = 0; j < i; j++) {
				if (i <= j + arr[j] && jumps[j] != Integer.MAX_VALUE) {
					jumps[i] = Math.min(jumps[i], jumps[j] + 1);
					break;
				}
			}
		}
		return jumps[n - 1];
	}

}
