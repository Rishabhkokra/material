package test;

// Java program to find minimum time required to make all
// oranges rotten

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

	public static int orangesRotting(int[][] grid) {
		int rows = grid.length;
		if (rows == 0)
			return 0;

		int cols = grid[0].length;
		Queue<int[]> queue = new LinkedList<>();
		int freshCount = 0;

		// Add all rotten oranges to queue and count fresh oranges
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (grid[i][j] == 2) {
					queue.offer(new int[] { i, j });
				} else if (grid[i][j] == 1) {
					freshCount++;
				}
			}
		}

		// Directions for adjacent cells (up, down, left, right)
		int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		int time = 0;

		// Continue until queue is empty or no fresh oranges remain
		while (!queue.isEmpty() && freshCount > 0) {
			int size = queue.size();

			// Process all oranges at current time level
			for (int i = 0; i < size; i++) {
				int[] current = queue.poll();
				int x = current[0];
				int y = current[1];

				// Check all adjacent cells
				for (int[] dir : directions) {
					int newX = x + dir[0];
					int newY = y + dir[1];

					// If adjacent cell is fresh orange, rot it
					if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1) {
						grid[newX][newY] = 2;
						queue.offer(new int[] { newX, newY });
						freshCount--;
					}
				}
			}

			time++;
		}

		// Return -1 if any fresh oranges remain
		return freshCount == 0 ? time : -1;
	}

	// Driver program
	public static void main(String[] args) {
		int arr[][] = { { 2, 1, 0, 2, 1 }, { 1, 0, 1, 2, 1 }, { 1, 0, 0, 2, 1 } };
		int ans = orangesRotting(arr);
		if (ans == -1)
			System.out.println("All oranges cannot rot");
		else
			System.out.println("Time required for all oranges to rot => " + ans);
	}
}
// This code is contributed by Sumit Ghosh
