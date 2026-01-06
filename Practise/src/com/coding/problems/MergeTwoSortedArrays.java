package com.coding.problems;

class MergeTwoSortedArrays {
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int k = nums1.length - 1;
		m--;
		n--;

		while (k >= 0) {

			if (m >= 0 && n >= 0 && nums1[m] <= nums2[n]) {
				nums1[k--] = nums2[n--];
			} else if (m >= 0) {
				nums1[k--] = nums1[m--];
			} else {
				nums1[k--] = nums2[n--];
			}
		}
	}

	public static void main(String[] args) {
		int[] array1 = { 1, 2, 3, 0, 0, 0 };
		int[] array2 = { 2, 5, 6 };
		merge(array1, 3, array2, 3);

		for (int i = 0; i < array1.length; i++) {
			System.out.print(array1[i] + ",");
		}
	}
}