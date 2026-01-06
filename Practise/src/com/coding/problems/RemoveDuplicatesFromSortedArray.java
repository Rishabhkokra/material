package com.coding.problems;

// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/?envType=study-plan-v2&envId=top-interview-150
public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {
		int array[] = { 1, 1, 1, 2, 2, 3 };
		remove(array);
		for (int i = 0; i < array.length; i++) {
			if(array[i]>0)
			System.out.print(array[i] + ",");
		}
	}

	private static void remove(int[] array) {
		for(int i=1;i<array.length;i++) {
			if(array[i]==array[i-1]) {
				array[i-1]=-1;
			}
		}
		
	}
//	private static void remove(int[] array) {
//
//		int left = 0, right = 0;
//
//		while (right < array.length) {
//			int count = 1;
//			while (right + 1 < array.length && array[right] == array[right + 1]) {
//				right++;
//				count++;
//			}
//			int countn = Math.min(2, count);
//			for (int i = 0; i < countn; i++) {
//				array[left] = array[right];
//				left++;
//			}
//			right++;
//
//		}
	}

}
