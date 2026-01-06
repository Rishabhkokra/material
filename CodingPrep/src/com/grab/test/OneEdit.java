package com.grab.test;

public class OneEdit {
//	An edit operation is defined as one of the following cases:
//		Adding a character to a string
//		Removing a character from a string
//		Replacing a character in a string with another character
//		The edit distance between two strings is the number of operations needed to transform one string to the other. Given two strings, write a function
//		is_one_edit_distance to determine if they have an edit distance of one.

	public static void main(String args[]) {
		boolean ans=isOneEditDistance("geek", "gesek");
		System.out.println(ans);
	}
	public static boolean isOneEditDistance(String s, String t) {
		// Make s always the longer string
		if (s.length() < t.length()) {
			return isOneEditDistance(t, s);
		}

		int m = s.length(), n = t.length();

		// If difference in length is more than 1, impossible to transform with one edit
		if (m - n > 1) {
			return false;
		}

		int count = 0;
		int i = 0, j = 0;

		while (i < m && j < n) {
			// If characters don't match
			if (s.charAt(i) != t.charAt(j)) {
				// If we've already found one difference, return false
				if (count == 1) {
					return false;
				}

				// Handle replacement case (when lengths are equal)
				if (m == n) {
					i++;
					j++;
				}
				// Handle insertion/deletion case (when lengths differ)
				else if (m > n) {
					i++;
				} else {
					j++;
				}

				count++;
			} else {
				i++;
				j++;
			}
		}

		// Check for extra character at end
		if (i < m || j < n) {
			count++;
		}

		return count <= 1;
	}
}