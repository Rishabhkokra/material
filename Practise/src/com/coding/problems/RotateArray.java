package com.coding.problems;

public class RotateArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int array[] = { 1, 2, 3, 4, 5, 6, 7 };

		rotate(array, 3);
	}

	private static void swap(int array[], int left, int right) {
		int tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;

	}

	private static void rotate(int[] array, int k) {

		int left = 0, right = array.length - 1;

		while (left != right) {
			swap(array, left, right);
			left++;
			right--;

		}
		printArray(array);

		System.out.println();

		left = 0;
		right = k - 1;

		while (left < right) {
			swap(array, left, right);
			left++;
			right--;

		}

		printArray(array);

		System.out.println();

		left = k;
		right = array.length - 1;

		while (left < right) {
			swap(array, left, right);
			left++;
			right--;
		}
		printArray(array);

	}

	private static void printArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ",");
		}
	}

}
