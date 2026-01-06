package com.grab.test;

public class GettingADifferentNumber {
	
	public static int getDifferentNumber(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		boolean[] present = new boolean[arr.length + 1];
		
		for (int num : arr) {
			if (num < present.length) {
				present[num] = true;
			}
		}

		for (int i = 0; i < present.length; i++) {
			if (!present[i]) {
				return i;
			}
		}

		return present.length;
	}

	public static void main(String[] args) {
		int[] arr = { 0, 1, 2, 3, 4, 9 };
		System.out.println("Smallest missing number: " + getDifferentNumber(arr));
	}
}