package com.coding.problems;

class Trie {
	static class TrieNode {

		TrieNode[] childNode;
		char data;
		boolean wordEnd;

		TrieNode(char data) {
			this.data = data;
			childNode = new TrieNode[26];
			wordEnd = false;
		}
	}

	TrieNode root;

	Trie() {
		char ch = '\n';
		root = new TrieNode(ch);
	}

	// Function to insert a key into the Trie
	void insert(String key) {

		TrieNode currentNode = root;
		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';
			if (currentNode.childNode[index] == null) {
				currentNode.childNode[index] = new TrieNode(key.charAt(i));
			}
			currentNode = currentNode.childNode[index];
		}
		currentNode.wordEnd = true;
	}

	// Function to search for a key in the Trie
	boolean search(String key) {
		TrieNode currentNode = root;
		for (int i = 0; i < key.length(); i++) {
			int index = key.charAt(i) - 'a';
			if (currentNode.childNode[index] == null) {
				return false;
			}
			currentNode = currentNode.childNode[index];
		}
		return currentNode.wordEnd;
	}
}

public class TrieDataStructure {
	public static void main(String[] args) {
		Trie trie = new Trie();
		String[] inputStrings = { "and", "ant", "do", "geek", "dad", "ball" };
		// Insert each string into the Trie
		for (String str : inputStrings) {
			trie.insert(str);
		}
		String[] searchQueryStrings = { "do", "geek", "bat" };
		// Search for each string and print whether it is
		// found in the Trie
		for (String query : searchQueryStrings) {
			System.out.println("Query String: " + query);
			if (trie.search(query)) {
				System.out.println("The query string is present in the Trie");
			} else {
				System.out.println("The query string is not present in the Trie");
			}
		}
	}
}