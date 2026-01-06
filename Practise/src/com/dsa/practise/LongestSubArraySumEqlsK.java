package com.dsa.practise;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArraySumEqlsK {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] array = { 2, 5, 21, 4,5, 10 };
		int k = 14;

//		System.out.println(find(array, k));
//		System.out.println(findOptimal(array, k));
		
		System.out.println("using prefix sum:"+ longestSubarrayWithSumKV2(array,k));

	}
	
	 public static int longestSubarrayWithSumKV2(int[] arr, int k) {
	        if (arr == null || arr.length == 0) return 0;
	        
	        // Map: prefix_sum -> first_index_where_this_sum_occurred
	        Map<Integer, Integer> sumToIndex = new HashMap<>();
	        
	        int maxLen = 0;
	        int cumulativeSum = 0;
	        
	        // Handle case where subarray starts from index 0
	        sumToIndex.put(0, -1);
	        
	        for (int currentIndex = 0; currentIndex < arr.length; currentIndex++) {
	            cumulativeSum += arr[currentIndex];
	            
	            // We need: cumulativeSum - previousSum = k
	            // So: previousSum = cumulativeSum - k
	            int requiredPreviousSum = cumulativeSum - k;
	            
	            if (sumToIndex.containsKey(requiredPreviousSum)) {
	                int previousIndex = sumToIndex.get(requiredPreviousSum);
	                int subarrayLength = currentIndex - previousIndex;
	                maxLen = Math.max(maxLen, subarrayLength);
	            }
	            
	            // Store first occurrence only (to get maximum length)
	            sumToIndex.putIfAbsent(cumulativeSum, currentIndex);
	        }
	        
	        return maxLen;
	    }

	private static int find(int[] array, int k) {

		int left = 0, right = 1, maxLength = 0;

		while (right < array.length) {

			int sum = 0;
			for (int i = left; i < right; i++) {
				sum = sum + array[i];
				if (sum <= k) {
					maxLength = Math.max((i - left) + 1, maxLength);
				} else if (sum > k) {
					left++;

				}
			}
			right++;
		}

		return maxLength;
	}

	private static int findOptimal(int[] array, int k) {

		int left = 0, right = 0, maxLength = 0;
		int sum = 0;
		while (right < array.length) {
			sum = sum + array[right];
			if (sum > k) {
				sum = sum - array[right];
				left = left + 1;
			}

			if (sum <= k) {
				maxLength = Math.max((right - left) + 1, maxLength);
			}
			right++;

		}
		return maxLength;
	}

}
