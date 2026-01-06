package com.coding.problems;

import java.util.*;

class FrequentWords {

	// Function that returns list of K
	// most frequent strings
	public static ArrayList<String> frequentWords(ArrayList<String> arr, int K) {

		// Hash map to store the frequency
		// of each string
		HashMap<String, Integer> Freq = new HashMap<>();

		// Set the default frequency of
		// each string 0
		for (String word : arr) {
			Freq.put(word, Freq.getOrDefault(word, 0) + 1);
		}

		// Using a priority queue to store
		// the strings in accordance of the
		// frequency and alphabetical order
		// (if frequency is equal)

		// Lambda expression is used set the
		// priority, if frequencies are not
		// equal than the string with greater
		// frequency is returned else the
		// string which is lexically smaller
		// is returned
		PriorityQueue<String> Order = new PriorityQueue<>(
				(a, b) -> (Freq.get(a) != Freq.get(b)) ? Freq.get(a) - Freq.get(b) : b.compareTo(a));

		// Traverse the HashMap
		for (String word : Freq.keySet()) {
			Order.offer(word);

			// Remove top (N - K) elements
			if (Order.size() > K) {
				Order.poll();
			}
		}

		// Order queue has K most frequent
		// strings in a reverse order
		ArrayList<String> ans = new ArrayList<>();

		while (!Order.isEmpty()) {
			ans.add(Order.poll());
		}

		// Reverse the ArrayList so as
		// to get in the desired order
		Collections.reverse(ans);

		return ans;
	}

	// Driver Code
	public static void main(String[] args) {
		int K = 2;

		// Given array
		ArrayList<String> arr = new ArrayList<String>();
		//arr.add("car");
		//arr.add("bus");
		arr.add("Rishabh");
		//arr.add("car");
		arr.add("Rishabh");
		arr.add("Honey");
		arr.add("Rishabh");

		// Function Call
		ArrayList<String> ans = frequentWords(arr, K);

		// Print the K most occurring strings
		for (String word : ans) {
			System.out.println(word + " ");
		}
	}
}