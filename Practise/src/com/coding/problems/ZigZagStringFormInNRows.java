package com.coding.problems;

// Java program to print string 
// obtained by concatenation
// of different rows of 
// Zig-Zag fashion
import java.util.Arrays;

public class ZigZagStringFormInNRows {

	// Prints concatenation
	// of all rows of str's
	// Zig-Zag fashion
	static void printZigZagConcat(String str, int n) {

		// Corner Case (Only one row)
		if (n == 1) {
			System.out.print(str);
			return;
		}
		char[] charArray = str.toCharArray();

		// Find length of string
		int len = str.length();

		// Create an array of
		// strings for all n rows
		String[] arr = new String[n];
		Arrays.fill(arr, "");

		// Initialize index for
		// array of strings arr[]
		int row = 0;
		boolean down = true; // True if we are moving
		// down in rows, else false

		// Traverse through
		// given string
		for (int i = 0; i < len; ++i) {
			// append current character
			// to current row
			arr[row] += (charArray[i]);

			if (down) {
				// If last row is reached,
				// change direction to 'up'
				if (row == n - 1) {
					down = false;
					row--;
				} else {
					row++;
				}
			} else {
				// If 1st row is reached,
				// change direction to 'down'
				if (row == 0) {
					down = true;
					row++;
				}
				// If direction is down,
				// increment, else decrement
				else {
					row--;
				}
			}
		}

		// Print concatenation
		// of all rows
		for (int i = 0; i < n; ++i) {
			System.out.print(arr[i]);
		}
	}

	// Driver Code
	public static void main(String[] args) {
		String str = "PAYPALISHIRING";
		int n = 3;
		printZigZagConcat(str, n);
	}
}

// This code is contributed by PrinciRaj1992
