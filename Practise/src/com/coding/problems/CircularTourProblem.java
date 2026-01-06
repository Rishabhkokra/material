package com.coding.problems;

public class CircularTourProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] gas = { 1, 2, 3, 4, 5 }, cost = { 3, 4, 5, 1, 2 };

		System.out.println(canCompleteCircuit(gas, cost));

	}

	static int canCompleteCircuit(int[] gas, int[] cost) {
		int n = gas.length;

		// determining if answer is possible
		int totalGas = 0;
		int totalCost = 0;
		for (int i = 0; i < n; i++) {
			totalGas += gas[i];
			totalCost += cost[i];
		}

		if (totalGas < totalCost) {
			return -1;
		}

		// now when answer is possible
		int remainingGas = 0;
		int start = 0;
		for (int i = 0; i < n; i++) {
			remainingGas = remainingGas + (gas[i] - cost[i]);
			if (remainingGas < 0) {
				start = i + 1;
				remainingGas = 0;
			}
		}
		return start;
	}
}