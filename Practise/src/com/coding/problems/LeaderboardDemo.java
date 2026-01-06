package com.coding.problems;

import java.util.*;

class Player implements Comparable<Player> {
	int id;
	int score;

	public Player(int id, int score) {
		this.id = id;
		this.score = score;
	}

	@Override
	public int compareTo(Player other) {
		// Sort by score in descending order
		if (this.score != other.score) {
			return Integer.compare(other.score, this.score);
		}
		// Break tie using ID in ascending order
		return Integer.compare(this.id, other.id);
	}

	@Override
	public String toString() {
		return "Player " + id + " : " + score;
	}
}

class Leaderboard {
	private TreeSet<Player> board = new TreeSet<>();
	private Map<Integer, Player> playerMap = new HashMap<>();

	// Add or update score
	public void addScore(int playerId, int score) {
		if (playerMap.containsKey(playerId)) {
			Player old = playerMap.get(playerId);
			board.remove(old); // first remove old entry
			old.score += score; // update score
			board.add(old); // add updated entry
		} else {
			Player newPlayer = new Player(playerId, score);
			board.add(newPlayer);
			playerMap.put(playerId, newPlayer);
		}
	}

	// Remove player from leaderboard
	public void reset(int playerId) {
		if (playerMap.containsKey(playerId)) {
			Player p = playerMap.remove(playerId);
			board.remove(p);
		}
	}

	// Get Top K players
	public List<Integer> top(int K) {
		List<Integer> result = new ArrayList<>();
		Iterator<Player> it = board.iterator();
		while (it.hasNext() && K-- > 0) {
			result.add(it.next().id);
		}
		return result;
	}

	public void printBoard() {
		for (Player p : board) {
			System.out.println(p);
		}
	}
}

public class LeaderboardDemo {
	public static void main(String[] args) {
		Leaderboard lb = new Leaderboard();
		lb.addScore(1, 50);
		lb.addScore(2, 80);
		lb.addScore(3, 70);
		lb.addScore(1, 40); // Player 1 now = 90

		System.out.println("Top 2: " + lb.top(2)); // [1, 2]
		System.out.println("\nFull Leaderboard:");
		lb.printBoard();

		lb.reset(1);
		System.out.println("\nAfter reset Player 1:");
		lb.printBoard();
	}
}
