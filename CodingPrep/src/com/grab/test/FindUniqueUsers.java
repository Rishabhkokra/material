package com.grab.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindUniqueUsers {

	private Map<String, Integer> nameToId = new HashMap<>();
	private Map<String, Integer> emailToId = new HashMap<>();
	private List<List<Integer>> adjacencyList;
	private int nextId = 0;

	// Add a node to the graph
	private int addNode(String value, boolean isName) {
		String mapKey = isName ? value : value.toLowerCase();

		if (!nameToId.containsKey(mapKey) && !emailToId.containsKey(mapKey)) {
			int id = nextId++;

			if (isName) {
				nameToId.put(mapKey, id);
			} else {
				emailToId.put(mapKey, id);
			}

			adjacencyList.add(new ArrayList<>());
			return id;
		}

		return isName ? nameToId.get(mapKey) : emailToId.get(mapKey);
	}

	// Build the graph from the input array
	public FindUniqueUsers(String[][] records) {
		adjacencyList = new ArrayList<>();

		for (String[] record : records) {
			String name = record[0];
			String email = record[1].toLowerCase();

			int nameId = addNode(name, true);
			int emailId = addNode(email, false);

			// Connect name and email nodes
			connectNodes(nameId, emailId);
		}
	}

	// Connect two nodes bidirectionally
	private void connectNodes(int i, int j) {
		adjacencyList.get(i).add(j);
		adjacencyList.get(j).add(i);
	}

	// Count connected components (unique users)
	public int countUniqueUsers() {
		boolean[] visited = new boolean[nextId];
		int components = 0;

		for (int i = 0; i < nextId; i++) {
			if (!visited[i]) {
				dfs(i, visited);
				components++;
			}
		}

		return components;
	}

	// Depth-first search helper
	private void dfs(int vertex, boolean[] visited) {
		visited[vertex] = true;

		for (int neighbor : adjacencyList.get(vertex)) {
			if (!visited[neighbor]) {
				dfs(neighbor, visited);
			}
		}
	}

	public static void main(String[] args) {
		String[][] records = { { "John Doe", "john.doe@gmail.com" }, { "John Walter Doe", "john.doe@gmail.com" },
				{ "Peter Watson", "peter@example.com" }, { "Peter Watson", "peter.watson@gmail.com" } };

		FindUniqueUsers graph = new FindUniqueUsers(records);
		System.out.println("Number of unique users: " + graph.countUniqueUsers());
	}

}
