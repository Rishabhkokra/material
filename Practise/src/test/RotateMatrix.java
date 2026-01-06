package test;

class RotateMatrix {
	
	
	private static void transpose(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i; j < matrix[0].length; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
		printMatrix(matrix);
	}

	private static void reverseRows(int[][] matrix) {
		for (int r = 0; r < matrix.length; r++) {
			int left = 0;
			int right = matrix.length - 1;

			while (left < right) {
				int temp = matrix[r][left];
				matrix[r][left] = matrix[r][right];
				matrix[r][right] = temp;

				left++;
				right--;
			}
		}

	}

	public static void rotate(int[][] matrix) {
		transpose(matrix);
		reverseRows(matrix);
	}

	public static void main(String ars[]) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		printMatrix(matrix);
		rotate(matrix);
		printMatrix(matrix);
	}

	private static void printMatrix(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + " ");
			}

			System.out.println();
		}
		System.out.println("\n");
	}
}