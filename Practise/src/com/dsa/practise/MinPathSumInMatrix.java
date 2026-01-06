package com.dsa.practise;

public class MinPathSumInMatrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[][] array = new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		for (int i = 0; i < array[0].length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println(countMin(array, 2, 2));
	}

	private static int countMin(int[][] array, int i, int j) {

		if (j == 0 || i == 0)
			return array[i][j];
		if (i < 0 || j < 0)
			return Integer.MAX_VALUE;
//		if (j >= array.length || i >= array.length)
//			return Integer.MIN_VALUE;

		int up = array[i][j] + countMin(array, i - 1, j);
		int left = array[i][j] + countMin(array, i, j - 1);

		return Math.min(left, up);
	}
}
