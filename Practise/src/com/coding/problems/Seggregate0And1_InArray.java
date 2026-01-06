package com.coding.problems;

// Java code to Segregate 0s and 1s in an array 
import java.io.*;

public class Seggregate0And1_InArray {

	// function to segregate 0s and 1s
	static void segregate0and1(int arr[], int size) {
		/* Initialize left and right indexes */
		int left = 0, right = size - 1;

		while (left < right) {
			/* Increment left index while we see 0 at left */
			while (arr[left] == 0 && left < right)
				left++;

			/* Decrement right index while we see 1 at right */
			while (arr[right] == 1 && left < right)
				right--;

			/*
			 * If left is smaller than right then there is a 1 at left and a 0 at right.
			 * Exchange arr[left] and arr[right]
			 */
			if (left < right) {
				arr[left] = 0;
				arr[right] = 1;
				left++;
				right--;
			}
		}
	}

	// function to print segregated array
	static void print(int arr[], int n) {
		System.out.print("Array after segregation is ");
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
	}

	// driver function
	public static void main(String[] args) {
		int arr[] = new int[] { 0, 1, 0, 1, 1, 1 };
		int n = arr.length;

		segregate0and1(arr, n);
		print(arr, n);

	}
}

// This code is contributed by Kamal Rawal 
