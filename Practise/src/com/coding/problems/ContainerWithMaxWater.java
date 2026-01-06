package com.coding.problems;

// Java code for Max 
// Water Container Given an array arr[] of non-negative integers, where each element arr[i] represents the height of the vertical lines, find the maximum amount of water that can be contained between any two lines, together with the x-axis.
import java.util.*;

class ContainerWithMaxWater {

	public static int maxArea(int A[], int len) {
		int l = 0;
		int r = len - 1;
		int area = 0;

		while (l < r) {
			// Calculating the max area
			area = Math.max(area, Math.min(A[l], A[r]) * (r - l));

			if (A[l] < A[r])
				l += 1;

			else
				r -= 1;
		}
		return area;
	}

	public static void main(String[] args) {
		int a[] = { 1, 5, 4, 3 };
		int b[] = { 3, 1, 2, 4, 5 };

		int len1 = 4;
		System.out.print(maxArea(a, len1) + "\n");

		int len2 = 5;
		System.out.print(maxArea(b, len2));
	}
}

// This code is contributed by rishabh_jain
