package test;

public class Floodfill2 {

	public static void main(String[] args) {
		int[][] array = { { 1, 1, 1, 1, 5 }, { 1, 1, 6, 1, 1 }, { 1, 7, 1, 0, 8 }, { 1, 1, 0, 1, 9 } };
		printArray(array);
		floodFill(array, 3, 1, 0, 0);
		printArray(array);

	}

	private static void floodFill(int[][] array, int newColour, int oldColour, int row, int col) {

		if (col == 4 && row == 1) {
			System.out.println("found");
		}
		if (row >= 5 || col >= 5 || row < 0 || col < 0)
			return;

		if (array[row][col] != oldColour) {
			return;
		}
		array[row][col] = newColour;
		floodFill(array, newColour, oldColour, row - 1, col);
		floodFill(array, newColour, oldColour, row + 1, col);
		floodFill(array, newColour, oldColour, row, col - 1);
		floodFill(array, newColour, oldColour, row, col + 1);

	}

	private static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j <= array.length; j++) {
				System.out.print(array[i][j] + " ");
			}

			System.out.println();
		}
		System.out.println("\n");
	}
}
