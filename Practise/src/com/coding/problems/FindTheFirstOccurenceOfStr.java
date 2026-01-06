package com.coding.problems;

public class FindTheFirstOccurenceOfStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String haystack = "sadbutbbsad", needle = "sad";

		System.out.println(findIndex(haystack, needle));

	}

	private static int findIndex(String haystack, String needle) {

		int index = -1, counter = 0;

		for (int i = 0; i < haystack.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(counter)) {
				if (index == -1) {
					index = i;
				}
				counter++;
				if (counter == needle.length())
					break;
			} else {
				index = -1;
				counter = 0;
			}
		}

		return index;

	}
}
