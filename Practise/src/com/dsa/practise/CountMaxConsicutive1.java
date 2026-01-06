package com.dsa.practise;

public class CountMaxConsicutive1 {

	public static void main(String[] args) {
		int[] prices = { 1, 1, 0, 1, 1, 1, 1, 1, 0, 0 };
		System.out.println(findConsicutive(prices));
	}

	private static int findConsicutive(int[] prices) {

		int left = 0, right = 0, maxLenght = 0, count = 0;

		while (right < prices.length) {

			if ( prices[right] > 0) {
				maxLenght = maxLenght + 1;
			} else {
				// when 0
				count = Math.max(maxLenght, count);
				maxLenght = 0;
			}
			right = right + 1;
		}

		return count;
	}

}
