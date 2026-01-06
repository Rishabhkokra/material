package test;

import java.util.Arrays;

class AllCombinationsOfCoins {

	public static int change(int amount, int[] coins) {
		
		int[][] dp = new int[coins.length + 1][amount + 1];

		// Initialize the first column with 1s
		for (int i = 0; i <= coins.length; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i <= coins.length; i++) {
			for (int j = 1; j <= amount; j++) {
				// If the current coin value is greater than the current amount
				if (j < coins[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					// Sum of solutions including and excluding the current coin
					dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
				}
			}
		}

		return dp[coins.length][amount];
	}

	public static void main(String ars[]) {
		int[] coins = { 1, 2, 5 };
		int amount = 5;
		System.out.println(change(amount, coins));
	}

}