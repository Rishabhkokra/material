package com.coding.problems;

class LongestPalinSubstring {
	// Function to print a substring str[low..high]
	static void printSubStr(String str, int low, int high) {
		for (int i = low; i <= high; ++i)
			System.out.print(str.charAt(i));
		System.out.println();
	}

	// Function to find the longest palindromic substring
	static int longestPalSubstr(String s) {
		int n = s.length();
		int start = 0, end = 1;
		int low, hi;

		// Traverse the input string
		for (int i = 0; i < n; i++) {

			// Find longest palindromic substring of even size
			low = i - 1;
			hi = i;

			// Expand substring while it is palindrome and in bounds
			while (low >= 0 && hi < n && s.charAt(low) == s.charAt(hi)) {

				// Update maximum length and starting index
				if (hi - low + 1 > end) {
					start = low;
					end = hi - low + 1;
				}
				low--;
				hi++;
			}

			// Find longest palindromic substring of odd size
			low = i - 1;
			hi = i + 1;

			// Expand substring while it is palindrome and in bounds
			while (low >= 0 && hi < n && s.charAt(low) == s.charAt(hi)) {

				// Update maximum length and starting index
				if (hi - low + 1 > end) {
					start = low;
					end = hi - low + 1;
				}
				low--;
				hi++;
			}
		}

		// Print the longest palindromic substring
		System.out.print("Longest palindrome substring is: ");
		printSubStr(s, start, start + end - 1);

		// Return output length
		return end;
	}

	// Driver code
	public static void main(String[] args) {
		String s = "forgeeksskeegfor";
		int length = longestPalSubstr(s);
		System.out.println("Length: " + length);
	}
}