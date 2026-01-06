package test;

import java.util.Arrays;

public class Anagram {

	public static void main(String[] args) {
		String s = "abcdebacb";
		String p = "cab";
		print(findAnagram(s, p));

		// --------------

		char str1[] = { 'g', 'r', 'a', 'm' };
		char str2[] = { 'a', 'r', 'm' };

		// Function Call
		if (areAnagram(str1, str2))
			System.out.println("The two strings are" + " anagram of each other");
		else
			System.out.println("The two strings are not" + " anagram of each other");

	}

	private static int[] findAnagram(String text, String search) {

		int[] searchHash = { 1, 1, 1, 0, 0 };
		int windowSide = 2;
		int[] output = { -1, -1, -1, -1, -1, -1, -1, -1, -1 };
		int count = 0;

		for (int i = 0; i < text.length(); i++) {
			int[] textHash = new int[5];

			for (int k = i; k <= (i + windowSide) && (i + windowSide < text.length()); k++) {
				int index = (Character.getNumericValue(text.charAt(k)) - 10);
				textHash[index] = 1;
			}
			if (checkIfArrayIsSame(searchHash, textHash)) {
				output[count] = i;
				count++;
			}
		}
		return output;
	}

	private static boolean checkIfArrayIsSame(int[] searchHash, int[] textHash) {
		return Arrays.equals(searchHash, textHash);
	}

	static boolean areAnagram(char[] str1, char[] str2) {
		// Get lengths of both strings
		int n1 = str1.length;
		int n2 = str2.length;

		// If length of both strings is not same,
		// then they cannot be anagram
		if (n1 != n2)
			return false;

		// Sort both strings
		Arrays.sort(str1);
		Arrays.sort(str2);

		// Compare sorted strings
		for (int i = 0; i < n1; i++)
			if (str1[i] != str2[i])
				return false;

		return true;
	}

	private static void print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] != -1)
				System.out.print(array[i] + " ");
		}
	}

}
