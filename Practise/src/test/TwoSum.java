package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TwoSum {
	public static int[] twoSum(int[] nums, int target) {

		int result[] = { -1, -1 };

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < nums.length; i++) {

			Integer existingNumIndex = map.getOrDefault(target - nums[i], -1);
			if (existingNumIndex != -1) {
				result[0] = existingNumIndex;
				result[1] = i;
				break;
			}
			map.put(nums[i], i);
		}

		return result;
	}

	public static void main(String srgs[]) {
		int array[] = { 2, 11, 1, 1, 7, 15 };
		int target = 9;
		int[] ans = twoSum(array, target);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}
}