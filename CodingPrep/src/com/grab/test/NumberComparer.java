package com.grab.test;

public class NumberComparer {
	public static boolean isStringNumberGreaterThan(String s, long n) {
		// Convert n to string for comparison
		String nStr = Long.toString(n);

		// If string number is longer, it's definitely larger
		if (s.length() > nStr.length()) {
			return true;
		}

		// If string number is shorter, it's definitely smaller
		if (s.length() < nStr.length()) {
			return false;
		}

		// If lengths are equal, compare digit by digit
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) > nStr.charAt(i)) {
				return true;
			} else if (s.charAt(i) < nStr.charAt(i)) {
				return false;
			}
		}

		// If we reach here, numbers are equal
		return false;
	}

	public static void main(String[] args) {
		// Test cases
		String s = "12147";
		long n = 12346L;

		boolean result = isStringNumberGreaterThan(s, n);
		System.out.println(s + " > " + n + ": " + result);
	}
}