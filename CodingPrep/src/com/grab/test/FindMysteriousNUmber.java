package com.grab.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FindMysteriousNUmber {

	private static void findX(int[] randomNum) {

		int[] ascSortedArray = Arrays.copyOf(randomNum, randomNum.length);
		Arrays.sort(ascSortedArray);

		int[] descSortedArray = Arrays.copyOf(randomNum, randomNum.length);
		reverseArray(descSortedArray);

		String desc = Arrays.stream(descSortedArray).mapToObj(String::valueOf).reduce("", String::concat);

		int descNum = Integer.parseInt(desc);

		String asc = Arrays.stream(ascSortedArray).mapToObj(String::valueOf).reduce("", String::concat);

		int ascNum = Integer.parseInt(asc);
		int result = descNum -ascNum;

		System.out.println(result);

	}

	// Helper method
	private static void reverseArray(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	}

	public static void main(String[] args) {
		int[] input = { 2, 4, 5, 6 };
		findX(input);

	}
}
