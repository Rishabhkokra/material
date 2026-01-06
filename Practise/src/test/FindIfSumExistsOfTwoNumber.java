package test;

//
//Example 1:
//Input Format: N = 5, arr[] = {2,6,5,8,11}, target = 14
//Result: YES (for 1st variant)
//       [1, 3] (for 2nd variant)
//Explanation: arr[1] + arr[3] = 14. So, the answer is “YES” for the first variant and [1, 3] for 2nd variant.
//
//Example 2:
//Input Format: N = 5, arr[] = {2,6,5,8,11}, target = 15
//Result: NO (for 1st variant)
//	[-1, -1] (for 2nd variant)
//Explanation: There exist no such two numbers whose sum is equal to the target.

public class FindIfSumExistsOfTwoNumber {

	public static String twoSum(int n, int[] arr, int target) {
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] + arr[j] == target)
					return "YES";
			}
		}
		return "NO";
	}

	public static void main(String args[]) {
		int n = 5;
		int[] arr = { 2, 6, 5, 8, 11 };
		int target = 14;
		String ans = twoSum(n, arr, target);
		System.out.println("This is the answer for variant 1: " + ans);
	}
}
