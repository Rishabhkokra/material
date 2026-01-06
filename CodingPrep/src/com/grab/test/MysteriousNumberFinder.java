package com.grab.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MysteriousNumberFinder {
	/**
	 * Finds the mysterious 4-digit number X where sorting digits descending minus
	 * ascending equals the original number.
	 */
	public static void main(String[] args) {
		// Example usage with different starting numbers
		int[] testNumbers = { 1234, 9999, 1111 };

		for (int start : testNumbers) {
			long startTime = System.nanoTime();
			Integer result = findMysteriousNumber(start);

			if (result != null) {
				System.out.printf("Found X = %d after %.2f milliseconds%n", result,
						(System.nanoTime() - startTime) / 1_000_000.0);
			} else {
				System.out.printf("No solution found for starting number %d%n", start);
			}
		}
	}

	/**
	 * Finds the mysterious X number starting from the given number.
	 * 
	 * @param start initial 4-digit number
	 * @return the mysterious number X, or null if none exists
	 */
	public static Integer findMysteriousNumber(int start) {
		// Validate input
		if (!isValidInput(start)) {
			return null;
		}

		Set<Integer> seen = new HashSet<>();

		int current = start;
		int iterations = 0;

		while (iterations < 1000 && !seen.contains(current)) {
			seen.add(current);

			// Get digits and sort them
			int[] digits = getDigits(current);
			int descending = createNumber(digits, true); // Sort descending
			int ascending = createNumber(digits, false); // Sort ascending

			current = descending - ascending;

			// Check if we found X
			if (current == start) {
				return current;
			}

			iterations++;
		}

		return null; // No solution found
	}

	/**
	 * Validates if the input is a valid 4-digit number with at least 2 distinct
	 * digits.
	 */
	private static boolean isValidInput(int num) {
		if (num < 1000 || num > 9999) {
			return false;
		}

		int[] digits = getDigits(num);
		Set<Integer> unique = new HashSet<>();
		for (int digit : digits) {
			unique.add(digit);
		}
		return unique.size() >= 2;
	}

	/**
	 * Extracts digits from a number into an array.
	 */
	private static int[] getDigits(int num) {
		String str = String.valueOf(Math.abs(num));
		int[] digits = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			digits[i] = Character.getNumericValue(str.charAt(i));
		}
		return digits;
	}

	/**
	 * Creates a number from sorted digits.
	 */
	private static int createNumber(int[] digits, boolean descending) {
		Arrays.sort(digits);
		if (!descending) {
			String asc = Arrays.stream(digits).mapToObj(String::valueOf).reduce("", String::concat);

			return Integer.parseInt(asc);

		}

		StringBuilder sb = new StringBuilder();
		for (int i = digits.length - 1; i >= 0; i--) {
			sb.append(digits[i]);
		}
		return Integer.parseInt(sb.toString());
	}
}