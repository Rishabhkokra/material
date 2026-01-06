package com.coding.problems;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ShortestPathInMatrix {

	// Function to check if coordinates are valid
	static boolean isValid(int x, int y, int N) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	// Function to find the shortest path from the first
	// cell to the last cell
	static int shortestPath(int[][] matrix, int N) {
		
		boolean[][] visited = new boolean[N][N];
		int[][] distance = new int[N][N];

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { 0, 0 });
		visited[0][0] = true;

		// Start Iterating
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int x = cell[0];
			int y = cell[1];
			int k = matrix[x][y];

			if (x == N - 1 && y == N - 1) {
				return distance[x][y];
			}

			int[] dx = { k, -k, 0, 0 };
			int[] dy = { 0, 0, k, -k };

			for (int i = 0; i < 4; i++) {
				int newX = x + dx[i];
				int newY = y + dy[i];

				// If coordinates are valid
				if (isValid(newX, newY, N) && !visited[newX][newY]) {
					queue.offer(new int[] { newX, newY });
					visited[newX][newY] = true;
					distance[newX][newY] = distance[x][y] + 1;
				}
			}
		}

		return -1;
	}

	// Driver code
	public static void main(String[] args) {
		int N = 3;

		int[][] matrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

		// Function call
		int result = shortestPath(matrix, N);
		System.out.println(result);
	}
}

// This code is contributed by shivamgupta310570
